class HistoryNode {
    String pageName;
    String pageId;
    String date;
    String siteURL;
    boolean bookmark;
    HistoryNode prev;
    HistoryNode next;

    public HistoryNode(String pageName, String pageId, String date, String siteURL, boolean bookmark) {
        this.pageName = pageName;
        this.pageId = pageId;
        this.date = date;
        this.siteURL = siteURL;
        this.bookmark = bookmark;
        this.prev = null;
        this.next = null;
    }
}
