package com.fh.api.cart.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fh.api.cart.model.Cart;
import com.fh.api.cart.model.CartInfo;
import com.fh.api.common.ResponseEnum;
import com.fh.api.common.ServerResponse;
import com.fh.api.member.model.Member;
import com.fh.api.product.mapper.ProductMapper;
import com.fh.api.product.model.Product;
import com.fh.api.util.BigDecimalUtil;
import com.fh.api.util.RedisUtil;
import com.fh.api.util.SystemConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public ServerResponse add(Integer productId, Integer count, Member member) {
        //判断商品是否存在
        Product product = productMapper.selectById(productId);
        if(product == null){
            return ServerResponse.error(ResponseEnum.PRODUCT_NULL);
        }
        //判断商品状态是否上架
        if(product.getIsUp() == 2){
            return ServerResponse.error(ResponseEnum.PRODUCT_DOWN);
        }
        //获取用户id
        //判断用户是否有购物车
        String cartStr = RedisUtil.hget(SystemConstant.REDIS_KEY_CART, member.getId().toString());
        if(cartStr==null){
            //没有购物车  新建购物车 并保存商品
            Cart cart = new Cart();
            return addCartInfo(count, product, member, cart);
        }
        //有购物车 判断是否有该商品 有两种情况
        Cart cart = JSON.parseObject(cartStr, Cart.class);
        List<CartInfo> cartInfoList = cart.getCartInfoList();
        CartInfo cartInfo = getCartInfo(productId, cartInfoList);

        if(cartInfo==null){
            //1.商品不存在 直接添加该商品
            return addCartInfo(count, product, member, cart);
        }
        //2.购物车已存在商品  更新商品数量
        return  updateCartInfo(cart,count,cartInfo,member.getId());
    }

    @Override
    public ServerResponse queryList(HttpServletRequest request) {
        Member member = (Member) request.getSession().getAttribute(SystemConstant.SESSION_MEMBER);
        String cartStr = RedisUtil.hget(SystemConstant.REDIS_KEY_CART, member.getId().toString());
        if(StringUtils.isEmpty(cartStr)){
            return ServerResponse.error(ResponseEnum.CART_NULL);
        }
        Cart cart = JSON.parseObject(cartStr, Cart.class);
        return ServerResponse.success(cart);
    }

    @Override
    public ServerResponse deleteCartPro(Integer productId,Member member) {
        String cartStr = RedisUtil.hget(SystemConstant.REDIS_KEY_CART, member.getId().toString());
        Cart cart = JSONObject.parseObject(cartStr, Cart.class);
        CartInfo cartInfo = new CartInfo();
        cartInfo.setProductId(productId);
        deleteCartInfo(cart,cartInfo);

        return updateCart(cart,member.getId());
    }

    private ServerResponse updateCartInfo(Cart cart, Integer count, CartInfo cartInfo, Integer memberId) {
        Integer totalCount = cartInfo.getCount() + count;
        if(totalCount <= 0){
            deleteCartInfo(cart,cartInfo);
        }else {
            cartInfo.setCount(totalCount);
            BigDecimal cartInfoPrice = BigDecimalUtil.mul(cartInfo.getCount().toString(), cartInfo.getPrice());
            cartInfo.setTotalPrice(cartInfoPrice.toString());
        }
        return updateCart(cart,memberId);
    }

    private void deleteCartInfo(Cart cart, CartInfo cartInfo) {
        List<CartInfo> cartInfoList = cart.getCartInfoList();
        Iterator<CartInfo> iterator = cartInfoList.iterator();
        while(iterator.hasNext()){
            CartInfo info = iterator.next();
            if(info.getProductId() == cartInfo.getProductId()){
                cartInfoList.remove(info);
                break;
            }
        }
    }

    private CartInfo getCartInfo(Integer productId, List<CartInfo> cartInfoList) {
        if(cartInfoList != null && cartInfoList.size() > 0){
            for (CartInfo cartInfo : cartInfoList) {
                if(cartInfo.getProductId() == productId){
                    return cartInfo;
                }
            }
        }
        return null;
    }

    private ServerResponse addCartInfo(Integer count, Product product, Member member, Cart cart) {
        CartInfo info = new CartInfo();
        info.setProductId(product.getId());
        info.setProductName(product.getNamePro());
        info.setCount(count);
        info.setFilePath(product.getFilePath());
        info.setPrice(product.getPrice().toString());
        BigDecimal totalPrice = BigDecimalUtil.mul(info.getPrice(), count.toString());
        info.setTotalPrice(totalPrice.toString());
        cart.getCartInfoList().add(info);
        //更新购物车
        return updateCart(cart, member.getId());
    }

    private ServerResponse updateCart(Cart cart, Integer memberId) {
        List<CartInfo> cartInfoList = cart.getCartInfoList();
        if(cartInfoList == null || cartInfoList.size() == 0){
            RedisUtil.hdel(SystemConstant.REDIS_KEY_CART,memberId.toString());
            return ServerResponse.success();
        }

        Integer totalCount = 0;
        BigDecimal totalPrice = new BigDecimal("0.00");
        for (CartInfo cartInfo : cartInfoList) {
            totalCount += cartInfo.getCount();
            totalPrice = BigDecimalUtil.add(totalPrice.toString(), cartInfo.getTotalPrice());
        }
        cart.setTotalCount(totalCount);
        cart.setTotalPrice(totalPrice.toString());

        String cartStr = JSONObject.toJSONString(cart);
        RedisUtil.hset(SystemConstant.REDIS_KEY_CART,memberId.toString(),cartStr);

        return ServerResponse.success();
    }
}
