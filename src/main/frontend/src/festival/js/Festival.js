import React, {useEffect, useState} from "react";
import Header from "../../global/header/Header";
import "../css/Festival.css";
import axios from "axios";

const Festival = () => {
    const [data, setData] = useState([]); // 전체 데이터
    const [filteredData, setFilteredData] = useState([]); // 필터링된 데이터
    const [currentPage, setCurrentPage] = useState(1); // 현재 페이지
    const [itemsPerPage] = useState(12); // 한 페이지에 표시할 항목 수
    const [error, setError] = useState(false); // 데이터 불러오기 실패 여부
    const [startDate, setStartDate] = useState(""); // 시작 날짜
    const [endDate, setEndDate] = useState(""); // 종료 날짜

    const totalPages = Math.ceil(filteredData.length / itemsPerPage);

    const base_url = "http://localhost:8080/festival/list";

    const fetchFestivals = async () => {
        try {
            const response = await axios.get(base_url);
            setData(response.data.dtoList);
            setFilteredData(response.data.dtoList); // 초기 필터링 데이터 설정
            setError(false); // 데이터 성공적으로 불러오면 에러 상태 초기화
        } catch (error) {
            console.error("Error fetching festivals:", error);
            setError(true); // 데이터 불러오기 실패 시 에러 상태 설정
        }
    };

    // 날짜 필터링 - 실시간 반영
    useEffect(() => {
        const filtered = data.filter((festival) => {
            const start = festival.eventStartDate ? new Date(festival.eventStartDate) : null;
            const end = festival.eventEndDate ? new Date(festival.eventEndDate) : null;

            const selectedStartDate = startDate ? new Date(startDate) : null;
            const selectedEndDate = endDate ? new Date(endDate) : null;

            // 필터링 조건
            if (selectedStartDate && selectedEndDate) {
                return (
                    (start && start <= selectedEndDate && end && end >= selectedStartDate) ||
                    (end && end >= selectedStartDate && start && start <= selectedEndDate)
                );
            } else if (selectedStartDate) {
                return (start && start >= selectedStartDate) || (end && end >= selectedStartDate);
            } else if (selectedEndDate) {
                return (end && end <= selectedEndDate) || (start && start <= selectedEndDate);
            }
            return true; // 날짜 선택이 없으면 전체 데이터 반환
        });
        console.log("Filtered Data:", filtered);
        setFilteredData(filtered);
        setCurrentPage(1); // 페이지 초기화
    }, [startDate, endDate, data]);

    useEffect(() => {
        fetchFestivals();
    }, []);

    const indexOfLastItem = currentPage * itemsPerPage;
    const indexOfFirstItem = indexOfLastItem - itemsPerPage;
    const currentItems = filteredData.slice(indexOfFirstItem, indexOfLastItem);

    // 페이지 변경 함수
    const paginate = (pageNumber) => setCurrentPage(pageNumber);

    return (
        <div className="content_festival">
            {!error && (
                <div className="date_filter">
                    <label>
                        시작 날짜:
                        <input
                            type="date"
                            value={startDate}
                            onChange={(e) => setStartDate(e.target.value)}
                        />
                    </label>
                    <label>
                        종료 날짜:
                        <input
                            type="date"
                            value={endDate}
                            onChange={(e) => setEndDate(e.target.value)}
                        />
                    </label>
                </div>
            )}

            {error ? (
                <div className="error_message">
                    <p>데이터를 불러오지 못했습니다. 잠시 후 다시 시도해주세요.</p>
                </div>
            ) : (
                <>
                    <div className="data_area">
                        {currentItems.map((festival, index) => (
                            <div key={index} className="content_container">
                                <span className="img_wrap">
                                    <img
                                        src={
                                            festival.firstimage2
                                                ? festival.firstimage2
                                                : `${process.env.PUBLIC_URL}/Image/noimg.png`
                                        }
                                        alt={festival.title}
                                    />
                                </span>
                                <p className="content_title">{festival.title}</p>
                            </div>
                        ))}
                    </div>

                    {totalPages > 1 && (
                        <div className="pagination_area">
                            <div className="pagination">
                                {Array.from({ length: totalPages }, (_, index) => (
                                    <button
                                        key={index + 1}
                                        onClick={() => paginate(index + 1)}
                                        className={currentPage === index + 1 ? "active" : ""}
                                    >
                                        {index + 1}
                                    </button>
                                ))}
                            </div>
                        </div>
                    )}
                </>
            )}
        </div>
    );
};

export default Festival;