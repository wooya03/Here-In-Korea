import React, {Component} from "react";
import ".../css/review.css";

class Reiview {
    constructor(props) {
        super(props);
        this.state = {
            searchTText: "",
            filteredDate: []
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

    handlWrire = () => {

    };

    handleDelete = () => {
        const { filteredData, clickWrire } = this.state;
    
        
      };

     handleModify = () => {

     }; 

    render(){
        const { filteredDate,searchTText } = this.state;

        return (
            <div className="">
                <h1>리뷰게시판</h1>
                <div className="review">
                    <input
                    type="text"
                    placeholder="revuew-name"
                    value={searchTText}
                    onChange={this.handlSearchTextChange}
                />
                </div>

                <button className="wrire-button" onClick={this.handlWrire}>작성하기</button>

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
                        {filteredDate.map((item) => (
                            <tr key={item.id}>
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
                    <spam>2</spam>
                    <spam>3</spam>
                    <span>4</span>
                    <spam>5</spam>
                    <spam>6</spam>
                </div>
            </div>
        )
    }
}

export default Reiview;