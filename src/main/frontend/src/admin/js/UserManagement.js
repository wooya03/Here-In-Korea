import React, { Component } from "react";
import "../css/UserManagement.css";
import "../css/Common.css";

class UserManagement extends Component {
  constructor(props) {
    super(props);
    this.state = {
      searchText: "",
      filteredData: [
        { id: 1, userId: "test123", name: "홍길동", nickname: "test1", joinDate: "2024-03-21 16:23", active: "O" },
        { id: 2, userId: "test456", name: "홍순자", nickname: "test2", joinDate: "2023-04-22 11:52", active: "X" },
        { id: 3, userId: "test789", name: "김기남", nickname: "test3", joinDate: "2023-06-11 22:45", active: "X" },
        { id: 4, userId: "test012", name: "김영수", nickname: "test4", joinDate: "2023-06-12 09:34", active: "X" },
        { id: 5, userId: "test345", name: "박지우", nickname: "test5", joinDate: "2023-07-11 08:12", active: "X" },
        { id: 6, userId: "test678", name: "강유리", nickname: "test6", joinDate: "2024-10-21 19:45", active: "X" }
      ],
      data: [
        { id: 1, userId: "test123", name: "홍길동", nickname: "test1", joinDate: "2024-03-21 16:23", active: "O" },
        { id: 2, userId: "test456", name: "홍순자", nickname: "test2", joinDate: "2023-04-22 11:52", active: "X" },
        { id: 3, userId: "test789", name: "김기남", nickname: "test3", joinDate: "2023-06-11 22:45", active: "X" },
        { id: 4, userId: "test012", name: "김영수", nickname: "test4", joinDate: "2023-06-12 09:34", active: "X" },
        { id: 5, userId: "test345", name: "박지우", nickname: "test5", joinDate: "2023-07-11 08:12", active: "X" },
        { id: 6, userId: "test678", name: "강유리", nickname: "test6", joinDate: "2024-10-21 19:45", active: "X" }
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
        <h1>회원정보조회</h1>
        <div className="search-user">
          <input
            type="text"
            placeholder="아이디 검색"
            value={searchText}
            onChange={this.handleSearchTextChange}
          />
          <input type="text" placeholder="닉네임 검색" />
          <p>남</p><input type="radio" name="gender" value="male" />
          <p>여</p><input type="radio" name="gender" value="female" />
          <p>휴면</p><input type="checkbox" name="dormancy" value="dormancy_yes" />
          <p>가입</p><input type="date"/>~<input type="date" />
          <button onClick={this.handleSearch}>조회</button>
        </div>

        <button className="delete-button" onClick={this.handleDelete}>
          DELETE
        </button>

        <table>
          <thead>
            <tr>
              <th>
                <input type="checkbox" />
              </th>
              <th>회원 아이디</th>
              <th>회원 이름</th>
              <th>닉네임</th>
              <th>가입일자</th>
              <th>승인상태</th>
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
                <td>{item.userId}</td>
                <td>{item.name}</td>
                <td>{item.nickname}</td>
                <td>{item.joinDate}</td>
                <td>{item.active}</td>
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

export default UserManagement;
