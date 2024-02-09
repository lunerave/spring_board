# Develop Environment
1. IDE: IntelliJ IDEA Community
2. Sring Boot 3.2.2
3. JDK 17
4. mysql
5. Spring Data JPA
6. Thymeleaf

# Board Functions
1. 글 쓰기(/board/save)
2. 글 목록(/board)
3. 글 조회(/board/{id})
4. 글 수정(/board/update/{id})
   - 상세화면에서 수정 버튼 클릭
   - 서버에서 해당 게시글의 정보를 가지고 수정화면 출력
   - 제목, 내용 수정 입력 받아서 서버로 요청
   - 수정 처리  
5. 글 삭제(/board/delete/{id})
6. 페이징처리(/board/paging)
   - /board/pagin?page=1
