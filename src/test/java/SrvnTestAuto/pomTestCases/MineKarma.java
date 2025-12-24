package SrvnTestAuto.pomTestCases;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import SrvnTestAuto.pageObjects.BasePage;
import SrvnTestAuto.pageObjects.ChapterPage;
import SrvnTestAuto.pageObjects.LoginPage;
import SrvnTestAuto.pageObjects.NovelPage;
import SrvnTestAuto.pageObjects.SeriesPage;
import SrvnTestAuto.reusables.TestReporter;
import SrvnTestAuto.testComponents.BaseTest;

public class MineKarma extends BaseTest {
	private static Logger log;

	@DataProvider (name = "dataProvForUsers", parallel = true)
	public Object[] dataProvForUsers() throws IOException {
		log = LogManager.getLogger();
		List<HashMap<String, String>> dataList = retrieveDataFromJson(
				"./src/main/java/SrvnTestAuto/data/WuxiaUserNamePassword.json");
		Object[] retOb = new Object[dataList.size()];

		for (int i = 0; i < dataList.size(); i++) {
			retOb[i] = dataList.get(i);
		}
		log.info("Data provider configured");
		return retOb;
	}

	@Test(groups = { "Karma" }, dataProvider = "dataProvForUsers")
	public void mineKarma(HashMap<String, String> data) throws IOException {
		TestReporter report = startTest(new Object() {
		}.getClass().getEnclosingMethod().getName());
		log.info("New test created");
		LoginPage login = new LoginPage(getDriver());

		BasePage basepage = login.login(data.get("userName"), data.get("password"));
		basepage.mineKarmaByLogin();
//		SeriesPage seriesPage = basepage.navigateToSeriesPage();
//		seriesPage.validateUserInSeriesPage();
//		seriesPage.selectChaptersFilter();
//		seriesPage.waitForNovelsToBeListed();
//		while (seriesPage.getCountOfNovelsListed() < 110) {
//			seriesPage.scrollToPageEnd();
//		}
//		System.out.println("No. of novels listed : " + seriesPage.getCountOfNovelsListed());
//		NovelPage novel = seriesPage
//				.selectSeries(seriesPage.getRandomNumber(0, seriesPage.getCountOfNovelsListed() - 1));
//		
//		novel.moveToChaptersTab();
//		novel.selectOldestSection();
//		novel.selectChapterByIndex(novel.getRandomNumber(0, 10));
//		
//		List<HashMap<String, String>> commentList = retrieveDataFromJson(
//				"./src/main/java/SrvnTestAuto/data/WuxiaPossibleComments.json");
//		
//		for (int i = 0; i < 5; i++) {
//			ChapterPage chapter = new ChapterPage(getDriver());
//			String currentchapterName = chapter.getChapterTitle();
//			chapter.addComments(commentList.get(chapter.getRandomNumber(0, commentList.size()-1)).get("comment"));
//			System.out.println("****Comment added for chapter : "+currentchapterName+"****");
//			if (i<4) {
//				chapter.moveToNextChapter(currentchapterName);
//				System.out.println("****moved from chapter : "+currentchapterName+"****");
//			}
//		}

		report.passTest();
		log.info("test passed");
	}

}
