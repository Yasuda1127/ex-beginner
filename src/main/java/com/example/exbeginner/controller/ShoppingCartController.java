package com.example.exbeginner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.exbeginner.model.Item;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;

import java.util.LinkedList;

@Controller
@RequestMapping("/shopping-cart")
public class ShoppingCartController {

    @Autowired
    private ServletContext servletContext;

    @GetMapping("/index")
    public String index(Model model, HttpSession session) {
        // 商品一覧の取得（applicationスコープ）
        @SuppressWarnings("unchecked")
        LinkedList<Item> itemList = (LinkedList<Item>) servletContext.getAttribute("itemList");
        if (itemList == null) {
            // 商品一覧が存在しない場合は新しく作成してapplicationスコープに格納
            itemList = new LinkedList<>();
            itemList.add(new Item("手帳ノート", 1000));
            itemList.add(new Item("文房具セット", 2000));
            itemList.add(new Item("ファイル", 1500));
            servletContext.setAttribute("itemList", itemList);
        }

        // ショッピングカートの取得（sessionスコープ）
        @SuppressWarnings("unchecked")
        LinkedList<Item> cart = (LinkedList<Item>) session.getAttribute("cart");
        if (cart == null) {
            // カートが存在しない場合は新しく作成してsessionスコープに格納
            cart = new LinkedList<>();
            session.setAttribute("cart", cart);
        }

        // 合計金額の計算とモデルへの格納
        int totalAmount = calculateTotalAmount(cart);
        model.addAttribute("totalAmount", totalAmount);

        // 商品一覧とショッピングカートをモデルに追加
        model.addAttribute("itemList", itemList);
        model.addAttribute("cart", cart);

        // item-and-cart.htmlにフォワード
        return "item-and-cart";
    }

    @PostMapping("/inCart")
    public String inCart(@RequestParam int index, HttpSession session) {
        // 商品一覧の取得（applicationスコープ）
        @SuppressWarnings("unchecked")
        LinkedList<Item> itemList = (LinkedList<Item>) servletContext.getAttribute("itemList");

        // ショッピングカートの取得（sessionスコープ）
        @SuppressWarnings("unchecked")
        LinkedList<Item> cart = (LinkedList<Item>) session.getAttribute("cart");

        // indexに対応する商品を取得してカートに追加
        if (itemList != null && cart != null && index >= 0 && index < itemList.size()) {
            Item selectedItem = itemList.get(index);
            cart.add(selectedItem);
        }

        // index()メソッドをリダイレクトにて呼び出す
        return "redirect:/shopping-cart/index";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam int index, HttpSession session) {
        // ショッピングカートの取得（sessionスコープ）
        @SuppressWarnings("unchecked")
        LinkedList<Item> cart = (LinkedList<Item>) session.getAttribute("cart");

        // indexに対応する商品をカートから削除
        if (cart != null && index >= 0 && index < cart.size()) {
            cart.remove(index);
        }

        // index()メソッドをリダイレクトにて呼び出す
        return "redirect:/shopping-cart/index";
    }

    private int calculateTotalAmount(LinkedList<Item> cart) {
        int totalAmount = 0;
        if (cart != null) {
            for (Item item : cart) {
                totalAmount += item.getPrice();
            }
        }
        return totalAmount;
    }
}
