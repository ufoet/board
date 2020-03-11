package action;

public class BoardActionFactory {
	private static BoardActionFactory baf;
	
	private BoardActionFactory() {}
	public static BoardActionFactory getInstance() {
		if(baf==null) {
			baf = new BoardActionFactory();
		}
		return baf;
	}
	
	Action action=null;
	public Action action(String cmd) {
		switch(cmd) {
			case "/qWrite.do" : action = new WriteAction("qList.do"); break;
			case "/qList.do" : action = new ListAction("view/qna_board_list.jsp"); break;
			case "/qHitUpdate.do" : action = new ReadcountUpdateAction("qView.do"); break;
			case "/qView.do" : action = new ViewAction("view/qna_board_view.jsp"); break;
			case "/qModify.do" : action = new ModifyAction("view/qna_board_modify.jsp"); break;
			case "/qUpdate.do" : action = new UpdateAction("qView.do"); break;
			case "/qDelete.do" : action = new DeleteAction("qList.do");break;
			case "/qReplyView.do" : action = new ReplyViewAction("view/qna_board_reply.jsp"); break;
			case "/qReply.do" : action = new ReplyAction("qList.do"); break;
			case "/qSearch.do" : action = new SearchAction("view/qna_board_list.jsp"); break;
		}
		return action;
	}
}







