package com.auction.sniper;

import com.objogate.wl.swing.AWTEventQueueProber;
import com.objogate.wl.swing.driver.JFrameDriver;
import com.objogate.wl.swing.driver.JLabelDriver;
import com.objogate.wl.swing.driver.JTableDriver;
import com.objogate.wl.swing.gesture.GesturePerformer;
import com.objogate.wl.swing.matcher.JLabelTextMatcher;
import org.hamcrest.Matchers;


import static com.objogate.wl.swing.matcher.IterableComponentsMatcher.matching;
import static com.objogate.wl.swing.matcher.JLabelTextMatcher.withLabelText;

/**
 * Created by H on 2018. 11. 26.
 */
public class AuctionSniperDriver extends JFrameDriver {
    public AuctionSniperDriver(int timeoutMillis) {
        super(new GesturePerformer(),
                JFrameDriver.topLevelFrame(named(MainWindow.MAIN_WINDOW_NAME), showingOnScreen()),
                new AWTEventQueueProber(timeoutMillis, 100));
    }

    public void showsSniperStatus(String itemId, int lastPrice, int lastBid, String statusText) {
//        new JTableDriver(this).hasCell(JLabelTextMatcher.withLabelText(Matchers.equalTo(statusText)));
        JTableDriver table = new JTableDriver(this);
        table.hasRow(
                matching(withLabelText(itemId),
                        withLabelText(String.valueOf(lastPrice)),
                        withLabelText(String.valueOf(lastBid)),
                        withLabelText(statusText))
        );
    }
}
