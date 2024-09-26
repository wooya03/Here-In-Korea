import React, { Component } from 'react';
import '../css/MemInfo.css';

class MemInfo extends Component {
  constructor(props) {
    super(props);
    this.state = {
      data: [
        { id: 1, userId: 'test123', name: '홍길동', nickname: 'test1', joinDate: '2024-03-21 16:23', active: 'O' },
        { id: 2, userId: 'test456', name: '홍순자', nickname: 'test2', joinDate: '2023-04-22 11:52', active: 'X' },
        { id: 3, userId: 'test789', name: '김기남', nickname: 'test3', joinDate: '2023-06-11 22:45', active: 'X' },
        { id: 4, userId: 'test012', name: '김영수', nickname: 'test4', joinDate: '2023-06-12 09:34', active: 'X' },
        { id: 5, userId: 'test345', name: '박지우', nickname: 'test5', joinDate: '2023-07-11 08:12', active: 'X' },
        { id: 6, userId: 'test678', name: '강유리', nickname: 'test6', joinDate: '2024-10-21 19:45', active: 'X' }
      ]
    };
  }

  isDelete = () => {
    alert('정말로 삭제하시겠습니까?');
  }
  render() {
    return (
      <div className="container">
        <h1>회원정보조회</h1>
        <div className="search-container">
            <input type="text" placeholder="아이디 검색" />
            <input type="text" placeholder="닉네임 검색" />
          남<input type="radio" name="gender" value="male" /> 
          여<input type="radio" name="gender" value="female" /> 
          휴면<input type="radio" name="dormancy" value="yes" /> 
          <input type="date" />
          <input type="date" />
          <button>조회</button>
        </div>
        <div>
        <button className='delete' onClick={this.isDelete}>DELETE</button>
        </div>
        <table>
          <thead>
            <tr>
              <th><input type="checkbox" /></th>
              <th>회원 아이디</th>
              <th>회원 이름</th>
              <th>닉네임</th>
              <th>가입일자</th>
              <th>승인상태</th>
            </tr>
          </thead>
          <tbody>
            {this.state.data.map((row) => (
              <tr key={row.id}>
                <td><input type="checkbox" /></td>
                <td>{row.userId}</td>
                <td>{row.name}</td>
                <td>{row.nickname}</td>
                <td>{row.joinDate}</td>
                <td>{row.active}</td>
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

export default MemInfo;