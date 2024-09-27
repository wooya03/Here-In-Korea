import React, { Component } from "react";
import "../css/CommentManagement.css";
import "../css/Common.css";

class CommentManagement extends Component {
  constructor(props) {
    super(props);
    this.state = {
      searchText: "",
      filteredData: [
        { id: 1, contents: "좋아요!", userId: "ync2024", commentDate: "2024-04-21"},
        { id: 2, contents: "잘 보고 가요",  userId: "test1", commentDate: "2023-04-21"},
        { id: 3, contents: "저도 가보고 싶네요!", userId: "test2", commentDate: "2023-04-21"},
        { id: 4, contents: "엄청 맛있어보여요", userId: "test012", commentDate: "2023-04-21"},
        { id: 5, contents: "너무 예뻐요ㅠㅠ",  userId: "test789", commentDate: "2023-04-21"}
      ],
      data: [
        { id: 1, contents: "좋아요!", userId: "ync2024", commentDate: "2024-04-21"},
        { id: 2, contents: "잘 보고 가요",  userId: "test1", commentDate: "2023-04-21"},
        { id: 3, contents: "저도 가보고 싶네요!", userId: "test2", commentDate: "2023-04-21"},
        { id: 4, contents: "엄청 맛있어보여요", userId: "test012", commentDate: "2023-04-21"},
        { id: 5, contents: "너무 예뻐요ㅠㅠ",  userId: "test789", commentDate: "2023-04-21"}
      ],
      selectedItems: [], // 선택된 항목을 저장
    };
  }

  handleSearchTextChange = (event) => {
    this.setState({ searchText: event.target.value });
  };

  handleSearch = () => {
    const { data, searchText } = this.state;
    const filtered = data.filter(
      (item) =>
        item.userId.includes(searchText) || item.nickname.includes(searchText)
    );
    this.setState({ filteredData: filtered });
  };

  handleSelectItem = (id) => {
    const { selectedItems } = this.state;
    const isSelected = selectedItems.includes(id);

    if (isSelected) {
      this.setState({
        selectedItems: selectedItems.filter((itemId) => itemId !== id)
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
      const updatedData = filteredData.filter(
        (item) => !selectedItems.includes(item.id)
      );
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
        <h1>댓글관리</h1>
        <div className="search-comment">
          <input
            type="text"
            placeholder="내용 검색"
            value={searchText}
            onChange={this.handleSearchTextChange}
          />
          <input type="text" placeholder="닉네임 검색" />
          <input type="date"/>~<input type="date" />
          <button onClick={this.handleSearch}>조회</button>
        </div>

        <button className="delete-button" onClick={this.handleDelete}>
          DELETE
        </button>

        <table>
          <thead>
            <tr>
              <th></th>
              <th>내용</th>
              <th>회원 아이디</th>
              <th>작성일자</th>
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
                <td>{item.contents}</td>
                <td>{item.userId}</td>
                <td>{item.commentDate}</td>
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

export default CommentManagement;