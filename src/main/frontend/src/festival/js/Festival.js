import React, {useEffect, useState} from "react";
import Header from "../../global/header/Header";
import "../css/Festival.css";
import axios from "axios";

const Festival = () => {
    const [data, setData] = useState([]); // 전체 데이터
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
    const [itemsPerPage] = useState(12); // 한 페이지에 표시할 항목 수

    const totalPages = Math.ceil(data.length / itemsPerPage);

    const fetchFestivals = async () => {
        try {
            const response = await axios.get("http://localhost:8080/festival/list");
            console.log(response.data.dtoList);
            console.log(response.data.dtoList);
            setData(response.data.dtoList);
        } catch (error) {
            console.error("Error fetching festivals:", error); // 오류 객체를 더 자세히 출력
            if (error.response) {
                // 서버가 응답했지만 상태 코드가 2xx가 아닌 경우
                console.error("Response error:", error.response.data);
            } else if (error.request) {
                // 요청은 보냈지만 응답을 받지 못한 경우
                console.error("Request error:", error.request);
            } else {
                // 기타 오류
                console.error("Error message:", error.message);
            }
        }
    };

    useEffect(() => {
        fetchFestivals();
    }, []);

    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = data.slice(indexOfFirstItem, indexOfLastItem);

    // 페이지 변경 함수
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="content_festival">
            <div className="data_area">
                {/* 데이터 목록 */}
                {currentItems.map((festival, index) => (
                    <div key={index} className="content_container">
                        {/* 이미지 썸네일 */}
                        <span className="img_wrap">
                            <img
                                src={festival.firstimage2 ? festival.firstimage2 : `${process.env.PUBLIC_URL}/Image/noimg.png`}
                                alt={festival.title}
                            />
                        </span>
                        {/* 제목 */}
                        <p className="content_title">{festival.title}</p>
                    </div>
                ))}
            </div>

            {/* 페이지 버튼 영역 */}
            {totalPages > 1 && (
                <div className="pagination_area">
                    <div className="pagination">
                        {Array.from({ length: totalPages }, (_, index) => (
                            <button
                                key={index + 1}
                                onClick={() => paginate(index + 1)}
                                className={currentPage === index + 1 ? 'active' : ''}
                            >
                                {index + 1}
                            </button>
                        ))}
                    </div>
                </div>
            )}
        </div>
    );
}

export default Festival