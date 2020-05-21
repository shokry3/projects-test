package com.app.shop.model.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.shop.model.dao.CartItemsRepository;
import com.app.shop.model.dao.ShoppingCartRepository;
import com.app.shop.model.dao.UserRepository;
import com.app.shop.model.enums.CartStatus;
import com.app.shop.model.pojo.Item;
import com.app.shop.model.pojo.ShoppingCart;
import com.app.shop.model.services.srinterface.IShopCartService;

import ch.qos.logback.core.joran.action.NewRuleAction;

@Service
public class ShopCartService implements IShopCartService {

	@Autowired
	ShoppingCartRepository cartRepo;
	
	@Autowired
	UserRepository userRepo;
	
	@Override
	public Optional<ShoppingCart> getCartById(long id) {
		System.out.println("begin get th e Item");
		try {
			return cartRepo.findById(id);
		}catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}

	@Override
	public List<ShoppingCart> getAllUserCarts(long userId) {
		try {
			return cartRepo.findByCartUser(userId);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public Optional<ShoppingCart> getUserCart(Long userId) {
		ShoppingCart newCart = new ShoppingCart();
		try {
			if (userId != null) {
				List<ShoppingCart> carts = cartRepo.findPendingCart(userId);
				// .stream().map(cart ->{newCart = cart;return newCart;})
				// .collect(Collectors.toList());
				if (carts.size() > 1) {
					newCart = carts.get(0);
				} else {
					newCart.setCartUser(userRepo.findById(userId).get());
					newCart.setPayed(false);
					newCart.setStatus(CartStatus.ADD);
					newCart = this.saveCart(newCart);
				}

			} else {
				//newCart.setCartUser(userRepo.findById(userId).get());
				newCart.setPayed(false);
				newCart.setStatus(CartStatus.ADD);
			}

			return Optional.of(newCart);
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

	@Override
	public ShoppingCart updateCart(ShoppingCart cart, ShoppingCart updatedCart) {
		cart.setStatus(updatedCart.getStatus());
		cart.setPayType(updatedCart.getPayType());
		cart.setPayed(updatedCart.isPayed());
		return this.saveCart(cart);
	}

	@Override
	public ShoppingCart addCart(ShoppingCart cart, long userId) {
		return this.saveCart(cart);
	}

	@Override
	public void deleteCart(ShoppingCart cart) {
		if (cart != null) {
			cartRepo.delete(cart);
		}
	}

	@Override
	public ShoppingCart saveCart(ShoppingCart cart) {
		try {
			cart.setTotalPrice(cartRepo.getCartTotalPrice(cart.getId()));
			cart = cartRepo.save(cart);
			return cart;
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}

}
