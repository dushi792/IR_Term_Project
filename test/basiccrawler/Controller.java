package edu.pitt.IRWebProject.crawler4j.basiccrawler;

/**
 * Created by zhaojun on 12/13/15.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.uci.ics.crawler4j.crawler.CrawlConfig;
import edu.uci.ics.crawler4j.crawler.CrawlController;
import edu.uci.ics.crawler4j.fetcher.PageFetcher;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtConfig;
import edu.uci.ics.crawler4j.robotstxt.RobotstxtServer;

public class Controller {
    private static final Logger logger = LoggerFactory.getLogger(Controller.class);

    public static void main(String[] args) throws Exception {
        if (args.length != 2) {
            logger.info("Needed parameters: ");
            logger.info("\t rootFolder (it will contain intermediate crawl data)");
            logger.info("\t numberOfCralwers (number of concurrent threads)");
            return;
        }

    /*
     * crawlStorageFolder is a folder where intermediate crawl data is
     * stored.
     */
        String crawlStorageFolder = args[0];

    /*
     * numberOfCrawlers shows the number of concurrent threads that should
     * be initiated for crawling.
     */
        int numberOfCrawlers = Integer.parseInt(args[1]);

        CrawlConfig config = new CrawlConfig();

        config.setCrawlStorageFolder(crawlStorageFolder);

        config.setPolitenessDelay(1000);

        // Unlimited number of pages can be crawled.
        config.setMaxPagesToFetch(100);

        config.setMaxDepthOfCrawling(-1);

    /*
     * Instantiate the controller for this crawl.
     */
        PageFetcher pageFetcher = new PageFetcher(config);
        RobotstxtConfig robotstxtConfig = new RobotstxtConfig();
        RobotstxtServer robotstxtServer = new RobotstxtServer(robotstxtConfig, pageFetcher);
        CrawlController controller = new CrawlController(config, pageFetcher, robotstxtServer);

    /*
     * For each crawl, you need to add some seed urls. These are the first
     * URLs that are fetched and then the crawler starts following links
     * which are found in these pages
     */
        controller.addSeed("http://stackoverflow.com/questions/2968/what-are-the-different-methods-to-parse-strings-in-java");

    /*
     * Start the crawl. This is a blocking operation, meaning that your code
     * will reach the line after this only when crawling is finished.
     */
        controller.startNonBlocking(BasicCrawler.class, numberOfCrawlers);

        // Wait for 30 seconds
        Thread.sleep(30 * 1000);

        // Send the shutdown request and then wait for finishing
        controller.shutdown();
        controller.waitUntilFinish();
    }
}