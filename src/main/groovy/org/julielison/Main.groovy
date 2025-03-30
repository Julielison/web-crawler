package org.julielison


import org.jsoup.nodes.Document

static void main(String[] args) {
	WebCrawler wc = new WebCrawler()
	Document docTiss = wc.runCommonTask()

	wc.runTask1(docTiss)
	wc.runTask2(docTiss)
	wc.runTask3(docTiss)
}