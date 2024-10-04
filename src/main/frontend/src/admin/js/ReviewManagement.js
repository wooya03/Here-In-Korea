import React, { Component } from "react";
import "../css/ReviewManagement.css";
import "../css/Common.css";

class ReviewManagement extends Component {
    constructor(props) {
        super(props);
        this.state = {
          searchText: "",
          filteredData: [
            { id: 1, title: "대전이 보유한 최고의 놀이터 심성당 리뷰", userId: "ync2024", date:"2024-04-21 15:44", views: 410230, likes: 21404 },
            { id: 2, title: "전라도 맛집 추천", userId: "ync2024", date:"2024-04-21 15:44", views: 130, likes: 42 },
            { id: 3, title: "애니 오타쿠를 위한 덕질 투어", userId: "ync2024", date:"2024-04-21 15:44", views: 180, likes: 32 },
            { id: 4, title: "도미는 여기가 맛있어요", userId: "test012", date:"2024-04-21 15:44", views: 1230, likes: 242 },
            { id: 5, title: "서울에서 가까운 꽃 축제 탐방", userId: "test789", date:"2024-04-21 15:44", views: 302, likes: 30 },
          ],
          data: [
            { id: 1, title: "대전이 보유한 최고의 놀이터 심성당 리뷰", userId: "ync2024", date:"2024-04-21 15:44", views: 410230, likes: 21404 },
            { id: 2, title: "전라도 맛집 추천", userId: "ync2024", date:"2024-04-21 15:44", views: 130, likes: 42 },
            { id: 3, title: "애니 오타쿠를 위한 덕질 투어", userId: "ync2024", date:"2024-04-21 15:44", views: 180, likes: 32 },
            { id: 4, title: "도미는 여기가 맛있어요", userId: "test012", date:"2024-04-21 15:44", views: 1230, likes: 242 },
            { id: 5, title: "서울에서 가까운 꽃 축제 탐방", userId: "test789", date:"2024-04-21 15:44", views: 302, likes: 30 },
          ],
          selectedItems: [], // 선택된 항목을 저장
        };
      }
    
      handleSearchTextChange = (event) => {
        this.setState({ searchText: event.target.value });
      };
    
      handleSearch = () => {
        const { data, searchText } = this.state;
        const filtered = data.filter((item) =>
          item.title.includes(searchText)
        );
        this.setState({ filteredData: filtered });
      };
    
      handleSelectItem = (id) => {
        const { selectedItems } = this.state;
        const isSelected = selectedItems.includes(id);
    
        if (isSelected) {
          this.setState({
            selectedItems: selectedItems.filter(itemId => itemId !== id)
          });
        } else {
          this.setState({
            selectedItems: [...selectedItems, id]
          });
        }
      };
    
      handleDelete = () => {
        const { filteredData, selectedItems } = this.state;
    
        if (selectedItems.length === 0) {
          alert("삭제할 항목을 선택하세요.");
          return;
        }
    
        if (window.confirm("정말 삭제하겠습니까?")) {
          const updatedData = filteredData.filter(item => !selectedItems.includes(item.id));
          this.setState({
            filteredData: updatedData,
            selectedItems: [] // 삭제 후 선택 항목 초기화
          });
        }
      };
    
      render() {
        const { filteredData, searchText, selectedItems } = this.state;
    
        return (
          <div className="app-container">
            <h1>리뷰게시판관리</h1>
            <div className="search-review">
              <input
                type="text"
                placeholder="게시글 이름"
                value={searchText}
                onChange={this.handleSearchTextChange}
              />
              <input type="text" placeholder="아이디 검색" />
              <input type="date" />~<input type="date" />
              <button onClick={this.handleSearch}>조회</button>
            </div>
    
            <button className="delete-button" onClick={this.handleDelete}>DELETE</button>
    
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
                {filteredData.map((item) => (
                  <tr key={item.id}>
                    <td>
                      <input
                        type="checkbox"
                        checked={selectedItems.includes(item.id)}
                        onChange={() => this.handleSelectItem(item.id)}
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
              <span>2</span>
              <span>3</span>
              <span>4</span>
              <span>5</span>
            </div>
          </div>
        )
    }
}

export default ReviewManagement;