package com.spring.webProject.command;

import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.spring.webProject.dao.IProductDao;
import com.spring.webProject.dao.IPurchaseListDao;
import com.spring.webProject.dto.ProductDto;
import com.spring.webProject.dto.PurchaseListDto;

public class AddBasketCommand implements ICommand {

	@Override
	public void execute(SqlSession sqlSession, Model model) {
		// TODO Auto-generated method stub
		/*
		 * IPurchaseListDao purdao = sqlSession.getMapper(IPurchaseListDao.class);
		 * IProductDao pdao = sqlSession.getMapper(IProductDao.class);
		 * 
		 * Map<String, Object> map = model.asMap(); String uId = (String)
		 * map.get("uId"); String pId = (String) map.get("pId");
		 * 
		 * //중복검사 String pIdCheck = purdao.getPidByUid(uId, pId);
		 * 
		 * if(pIdCheck == null) { //중복이 있을때 model.addAttribute("result",
		 * "alreadyAdded"); return; }
		 * 
		 * ProductDto product = pdao.getProduct(pId); //pId에 해당하는 정보가져오기
		 * 
		 * //data에서 가격, 컬러값, 현재시간은 내가 여기서 따로 넣어주어야한다. PurchaseListDto purchase =
		 * purdao.addBasketItem(uId,pId,product.getpName(),컬러??,가격??,product.
		 * getpImageRoute(), 시간??, "basket");
		 */
		/*
		 * 
		 * 여기서 받아온 pId와 실제 pId가 같다면. 
		 * uId와 pId값을 넣어주고 
		 * pId값을 통해서 product정보를 또 가져와야함.
		 * 그 정보들을 싹다 purchaselist db에 넣어주어야함. 
		 * */
		
		//중복검사
//		String bookmark = dao.getBookmarkList(uId);
//		System.out.println("북마크리스트받아온것 "+bookmark);
//		
//		if(bookmark != null) {
//			String bookmarkList[] = bookmark.split(";");
//		
//			for(int i=0;i<bookmarkList.length;i++) { 
//				if(pId.equals(bookmarkList[i])) {
//					model.addAttribute("result", "alreadyAdded");
//					return;
//				}
//			}
//		
//			pIdAdd+=bookmark;
//		}
		
		/*
		 * try { //dao.addBookmark(uId, pIdAdd); model.addAttribute("result",
		 * "success"); } catch(Exception e) { System.out.println("관심목록 추가 예외발생");
		 * model.addAttribute("result", "fail. ask to manager"); }
		 */
	}

}
