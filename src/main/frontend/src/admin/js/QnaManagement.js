import React, { Component } from "react";
import "../css/QnaManagement.css";
import "../css/Common.css";

class QnaManagement extends Component {
    constructor(props) {
        super(props);
        this.state = {
          searchText: "",
          filteredData: [
            { id: 1, title: "코스 등록 문의", userId: "ync2024", group:"코스문의", date:"2024-04-21 15:44", status:"답변대기" },
            { id: 2, title: "나무호텔 문의 드립니다", userId: "test2", group:"숙소문의", date:"2024-04-21 15:44", status:"답변대기"},
            { id: 3, title: "한강 서래섬 유채꽃 축제 주차공간", userId: "ync2024", group:"행사문의", date:"2024-04-21 15:44", status:"답변완료"},
            { id: 4, title: "5월 행사 관련 문의", userId: "test012", group:"행사문의", date:"2024-04-21 15:44", status:"답변완료"},
            { id: 5, title: "리뷰 관련 문의", userId: "test789", group:"리뷰문의", date:"2024-04-21 15:44", status:"답변완료"},
          ],
          data: [
            { id: 1, title: "코스 등록 문의", userId: "ync2024", group:"코스문의", date:"2024-04-21 15:44", status:"답변대기" },
            { id: 2, title: "나무호텔 문의 드립니다", userId: "test2", group:"숙소문의", date:"2024-04-21 15:44", status:"답변대기"},
            { id: 3, title: "한강 서래섬 유채꽃 축제 주차공간", userId: "ync2024", group:"행사문의", date:"2024-04-21 15:44", status:"답변완료"},
            { id: 4, title: "5월 행사 관련 문의", userId: "test012", group:"행사문의", date:"2024-04-21 15:44", status:"답변완료"},
            { id: 5, title: "리뷰 관련 문의", userId: "test789", group:"리뷰문의", date:"2024-04-21 15:44", status:"답변완료"},
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
            <h1>QNA</h1>
            <div className="search-qna">
              <input
                type="text"
                placeholder="문의 제목"
                value={searchText}
                onChange={this.handleSearchTextChange}
              />
              <input type="text" placeholder="아이디 검색" />
              <select>
                <option value="">문의구분</option>
                <option value="숙소문의">숙소문의</option>
                <option value="행사문의">행사문의</option>
                <option value="코스문의">코스문의</option>
                <option value="기타문의">기타문의</option>
              </select>
              <p>답변대기</p><input type="checkbox" value="answer"/>
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
                  <th>문의구분</th>
                  <th>작성일</th>
                  <th>답변상태</th>
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
                    <td>{item.group}</td>
                    <td>{item.date}</td>
                    <td>{item.status}</td>
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

export default QnaManagement;