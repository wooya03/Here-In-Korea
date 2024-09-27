import React, { Component } from "react";
import "../css/CourseManagement.css";

class CourseManagement extends Component {
  constructor(props) {
    super(props);
    this.state = {
      searchText: "",
      filteredData: [
        { id: 1, title: "대구 투어!", userId: "test123", views: 1400, likes: 2 },
        { id: 2, title: "누구나 가기 좋은 익산 나들이 코스", userId: "test456", views: 130, likes: 5 },
        { id: 3, title: "서울 당일치기 코스", userId: "test789", views: 1800, likes: 3 },
        { id: 4, title: "청동기 시대로 떠나는 여행", userId: "test789", views: 123, likes: 10 },
        { id: 5, title: "지평선의 고장, 김제의 들녘을 가다", userId: "test012", views: 2302, likes: 33 },
      ],
      data: [
        { id: 1, title: "대구 투어!", userId: "test123", views: 1400, likes: 2 },
        { id: 2, title: "누구나 가기 좋은 익산 나들이 코스", userId: "test456", views: 130, likes: 5 },
        { id: 3, title: "서울 당일치기 코스", userId: "test789", views: 1800, likes: 3 },
        { id: 4, title: "청동기 시대로 떠나는 여행", userId: "test789", views: 123, likes: 10 },
        { id: 5, title: "지평선의 고장, 김제의 들녘을 가다", userId: "test012", views: 2302, likes: 33 },
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
        <h1>코스게시판관리</h1>
        <div className="search-container">
          <input
            type="text"
            placeholder="코스 이름"
            value={searchText}
            onChange={this.handleSearchTextChange}
          />
          <input type="text" placeholder="아이디 검색" />
          <select>
            <option value="">지역</option>
            <option value="서울">서울</option>
            <option value="대구">대구</option>
          </select>
          <input type="date" />
          <input type="date" />
          <button onClick={this.handleSearch}>조회</button>
        </div>

        <button className="delete-button" onClick={this.handleDelete}>DELETE</button>

        <table>
          <thead>
            <tr>
              <th></th>
              <th>제목</th>
              <th>아이디</th>
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
    );
  }
}

export default CourseManagement;
