class BrowserHistory {
    private HistoryNode head;
    private HistoryNode tail;
    private HistoryNode[] stack;
    private int stackPointer;

    public BrowserHistory() {
        this.head = null;
        this.tail = null;
        this.stack = new HistoryNode[1000];
        this.stackPointer = -1;
    }

    public void addPage(String pageName, String pageId, String date, String siteURL, boolean bookmark) {
        HistoryNode newNode = new HistoryNode(pageName, pageId, date, siteURL, bookmark);
        if (head == null) {
            head = newNode;
            tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        pushToStack(newNode);
    }

    private void pushToStack(HistoryNode node) {
        if (stackPointer == stack.length - 1) {
            resizeStack();
        }
        stack[++stackPointer] = node;
    }

    private void resizeStack() {
        HistoryNode[] newStack = new HistoryNode[stack.length * 2];
        System.arraycopy(stack, 0, newStack, 0, stack.length);
        stack = newStack;
    }

    public void deletePage(String pageId) {
        HistoryNode current = head;
        while (current != null) {
            if (current.pageId.equals(pageId)) {
                if (current.prev != null) {
                    current.prev.next = current.next;
                } else {
                    head = current.next;
                }
                if (current.next != null) {
                    current.next.prev = current.prev;
                } else {
                    tail = current.prev;
                }
                removeFromStack(current);
                return;
            }
            current = current.next;
        }
    }

    private void removeFromStack(HistoryNode node) {
        for (int i = 0; i <= stackPointer; i++) {
            if (stack[i] == node) {
                for (int j = i; j < stackPointer; j++) {
                    stack[j] = stack[j + 1];
                }
                stack[stackPointer--] = null;
                return;
            }
        }
    }

    public void displayHistoryInReverse() {
        for (int i = stackPointer; i >= 0; i--) {
            HistoryNode node = stack[i];
            System.out.println(node.pageName + " " + node.pageId + " " + node.date + " " + node.siteURL + " " + node.bookmark);
        }
    }

    public void displayBookmarkedSites() {
        HistoryNode current = head;
        while (current != null) {
            if (current.bookmark) {
                System.out.println(current.pageName + " " + current.pageId + " " + current.date + " " + current.siteURL + " " + current.bookmark);
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        BrowserHistory history = new BrowserHistory();
        history.addPage("YouTube", "001A", "10.08.2023", "https://www.youtube.com/", false);
        history.addPage("GeeksforGeeks", "011B", "09.08.2023", "https://www.geeksforgeeks.org/", true);
        history.addPage("Tutorialspoint", "012C", "06.08.2023", "https://www.tutorialspoint.com/index.htm", true);
        history.addPage("Stackoverflow", "003D", "05.08.2023", "https://stackoverflow.com/", false);

        System.out.println("History in reverse order:");
        history.displayHistoryInReverse();

        System.out.println("\nBookmarked sites:");
        history.displayBookmarkedSites();

        System.out.println("\nDeleting GeeksforGeeks:");
        history.deletePage("011B");

        System.out.println("\nHistory in reverse order after deletion:");
        history.displayHistoryInReverse();

        System.out.println("\nBookmarked sites after deletion:");
        history.displayBookmarkedSites();
    }
}
