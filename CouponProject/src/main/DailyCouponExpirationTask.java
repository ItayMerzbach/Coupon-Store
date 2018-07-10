package main;

import java.util.Date;
import Exceptions.CouponServerException;
import dao.db.CouponDAODB;

public class DailyCouponExpirationTask implements Runnable {

	
	private CouponDAODB couponDAODB = null;
	private boolean quit = true;

	/**
	 * CONSTRUCTOR
	 */
	public DailyCouponExpirationTask() {
		try {
			this.couponDAODB = new CouponDAODB();
		} catch (CouponServerException e) {
			try {
				throw new CouponServerException("eror in create DailyCouponExpirationTask", e);
			} catch (CouponServerException e1) {
			}
		}
	}

	/**
	 * THREADS CHECKS OUT IF COUPONS EXPIRATION DATE IS BEFORE TODAY IF TRUE REMOVE EXPIREF COUPONS FROM DATA BASE
	 */
	@Override
	public void run() {
		while (quit) {

			java.sql.Date SQLDate = new java.sql.Date(new Date().getTime());

			try {
				couponDAODB.removeCouponByDate(SQLDate);
			} catch (CouponServerException e1) {
				try {
					throw new CouponServerException("eror in delete past coupons", e1);
				} catch (CouponServerException e) {
				}
			}

			try {
				Thread.sleep(8640000);
				// close BufferedWriter to report the errors to the system once a day
				CouponServerException.closeBufferedWriter();
			} catch (InterruptedException | CouponServerException e) {
			}
		}
	}
}
