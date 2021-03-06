package com.auction.sniper;

/**
 * Created by H on 2018. 11. 26.
 */
public class ApplicationRunner {
    public static final String XMPP_HOSTNAME = "172.23.255.6";
    public static final String SNIPER_XMPP_ID = "sniper@172.23.255.6/Auction";
    public static final String SNIPER_ID = "sniper";
    public static final String SNIPER_PW = "sniper";

    private AuctionSniperDriver driver;

    private String itemId;

    public void startBiddingIn(final FakeAuctionServer auction) {
        this.itemId = auction.getItemId();
        Thread thread = new Thread("Test Application") {
            @Override
            public void run() {
                try {
                    Main.main(XMPP_HOSTNAME, SNIPER_ID, SNIPER_PW, auction.getItemId());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        thread.setDaemon(true);
        thread.start();
        driver = new AuctionSniperDriver(1000);
        driver.showsSniperStatus(itemId, 0, 0, MainWindow.STATUS_JOINING);
    }

    public void showsSniperHasLostAuction() {
        driver.showsSniperStatus(itemId, 0, 0, MainWindow.STATUS_LOST);
    }

    public void hasShownSniperIsBidding(int lastPrice, int lastBid) {
        driver.showsSniperStatus(itemId, lastPrice, lastBid, MainWindow.STATUS_BIDDING);
    }

    public void hasShownSniperIsWinning(int winningBid) {
        driver.showsSniperStatus(itemId, winningBid, winningBid, MainWindow.STATUS_WINNING);
    }

    public void showsSniperHasWonAuction(int lastPrice) {
        driver.showsSniperStatus(itemId, lastPrice, lastPrice, MainWindow.STATUS_WON);
    }

    public void stop() {
        if(null != driver) {
            driver.dispose();
        }
    }
}
