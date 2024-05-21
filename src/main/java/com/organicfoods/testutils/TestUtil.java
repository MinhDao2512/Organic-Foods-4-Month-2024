package com.organicfoods.testutils;

import com.organicfoods.util.EmailUtil;

public class TestUtil {
	public static void main(String[] args) {
		Boolean check = EmailUtil.getInstance().sendTo("n20dcvt009@student.ptithcm.edu.vn", "Thông báo đơn hàng", "Đăt hàng thành công");
		if(check) {
			System.out.println("Thanh Cong!");
		}
		else {
			System.out.println("That Bai!");
		}
	}
}
