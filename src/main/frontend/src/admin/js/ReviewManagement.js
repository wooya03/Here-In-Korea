import React, { useEffect, useState } from "react";
import "../css/ReviewManagement.css";
import "../css/Common.css";
import { format } from 'date-fns';
import axios from 'axios';
import { useNavigate } from "react-router-dom";

const ReviewManagement = () => {
      const baseUrl = "http://localhost:8080";
      const [data, setData] = useState([]); // 현재 페이지 데이터
      const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
      const [totalPages, setTotalPages] = useState(0); // 총 페이지 수
      const [itemsPerPage] = useState(10); // 페이지당 항목 수
      const navigate = useNavigate();
    
        useEffect(() => {
          putSpringData(currentPage); // 컴포넌트 로드 시 데이터 요청
        }, [currentPage]);

        async function putSpringData(pageNumber) {
          try {
            const params = {
              page: pageNumber,
              size: itemsPerPage,
            };
      
            const response = await axios.get(baseUrl + "/admin/review", { params });
            const transformedData = response.data.dtoList
              ? response.data.dtoList.map(item => ({
                  id: item.id,
                  title: item.title,
                  userId: item.memId,
                  date: item.createdDate,
                  views: item.views,
                  likes: item.likes
                }))
              : [];
            setData(transformedData);
            setTotalPages(response.data.totalPage);
          } catch (error) {
            console.error("데이터를 가져오는 중 오류 발생:", error);
          }
        }
    
      // const handleDelete = () => {
        // const { filteredData, selectedItems } = this.state;
    
        // if (selectedItems.length === 0) {
        //   alert("삭제할 항목을 선택하세요.");
        //   return;
        // }
    
        // if (window.confirm("정말 삭제하겠습니까?")) {
        //   const updatedData = filteredData.filter(item => !selectedItems.includes(item.id));
        //   this.setState({
        //     filteredData: updatedData,
        //     selectedItems: [] // 삭제 후 선택 항목 초기화
        //   });
        // }
      // };
    
        return (
          <div className="app-container">
            <h1>리뷰게시판관리</h1>
            <div className="search-review">
              {/* <input
                type="text"
                placeholder="게시글 이름"
                value={searchText}
                onChange={this.handleSearchTextChange}
              />
              <input type="text" placeholder="아이디 검색" />
              <button onClick={this.handleSearch}>조회</button> */}
            </div>
    
            {/* <button className="delete-button" onClick={this.handleDelete}>DELETE</button>
     */}
            <table>
              <thead>
                <tr>
                  <th></th>
                  <th>제목</th>
                  <th>아이디</th>
                  <th>작성일</th>
                  <th>조회수</th>
                  <th>추천</th>
                </tr>
              </thead>
              <tbody>
                {data.map((item) => (
                  <tr key={item.id}>
                    <td>
                      <input
                        type="checkbox"
                      />
                    </td>
                    <td>{item.title}</td>
                    <td>{item.userId}</td>
                    <td>{item.date}</td>
                    <td>{item.views}</td>
                    <td>{item.likes}</td>
                  </tr>
                ))}
              </tbody>
            </table>
    
            <div className="pagination">
              <span>1</span>
            </div>
          </div>
        )
}

export default ReviewManagement;