import React, { useEffect, useState } from 'react';
import '../css/HotelsList.css';
import { format } from "date-fns";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import { useParams } from 'react-router-dom';

function formatTime(dateString) {
    const date = new Date(dateString);
    return format(date, "yyyy-MM-dd h:mm:ss a");
}

function HotelsList() {
    const baseUrl = "http://localhost:8080";
    const [data, setData] = useState([]);
    const { id } = useParams();
    const [order, setOrder] = useState("asc");  // 'asc'로 초기화
    const [area, SetArea] = useState(0);
    
    useEffect(() => {
        putSpringData();
    }, [order, area]);  // order가 변경될 때마다 데이터를 새로 가져오도록 함

    const navigate = useNavigate();
    
    const detailsClick = (id) => {
        navigate(`/hotels/${id}`);
    };

    async function putSpringData() {
        await axios
            .get(baseUrl + `/hotels/list?order=${order}&areaCode=${area}`)  // order 파라미터 추가
            .then((res) => {
                const transformedData = res.data.dtoList
                    ? res.data.dtoList.map((item) => {
                        return {
                            id: item.contentid,
                            title: item.title,
                            areaCode: item.areacode,
                            modifiedDate: item.modifiedDate,
                            thumbnail: item.firstimage2,
                        };
                    })
                    : [];
                setData(transformedData);
            })
            .catch((err) => {
                console.log(err);
            });
    }

    function areastring(res) {
        switch (res) {
            case 1:
                return "#서울";
            case 2:
                return "#인천";
            case 3:
                return "#대전";
            case 4:
                return "#대구";
            case 5:
                return "#광주";
            case 6:
                return "#부산";
            case 7:
                return "#울산";
            case 8:
                return "#세종";
            case 31:
                return "#경기";
            case 32:
                return "#강원";
            case 33:
                return "#충북";
            case 34:
                return "#충남";
            case 35:
                return "#경북";
            case 36:
                return "#경남";
            case 37:
                return "#전북";
            case 38:
                return "#전남";
            case 39:
                return "#제주";
            default:
                return "";
        }
    }

    const handleOrderChange = (newOrder) => {
        setOrder(newOrder);
    };

    const handleAreaChange = (e) => {
        SetArea(e.target.value); 
      };

    return (
        <div>
            <div className='hotel-button'>
                <button onClick={() => handleOrderChange("desc")}>내림차순</button>
                <button onClick={() => handleOrderChange("asc")}>오름차순</button>
                <select onChange={handleAreaChange} value={area}>
                    <option value="0">문의 구분</option>
                    <option value="1">서울</option>
                    <option value="2">인천</option>
                    <option value="3">대전</option>
                    <option value="4">대구</option>
                    <option value="5">광주</option>
                    <option value="6">부산</option>
                    <option value="7">울산</option>
                    <option value="8">세종</option>
                    <option value="31">경기</option>
                    <option value="32">강원</option>
                    <option value="33">충북</option>
                    <option value="34">충남</option>
                    <option value="35">경북</option>
                    <option value="36">경남</option>
                    <option value="37">전북</option>
                    <option value="38">전남</option>
                    <option value="39">제주</option>
                </select>   
            </div>
            <div>
                
            </div>
            <div className="hotels-container">
                {data.length > 0 ? (
                    data.map((datas, index) => (
                        <div key={index} className="hotel-card">
                            <div className="hotel-image" onClick={() => detailsClick(datas.id)}>
                                {datas.thumbnail ? (
                                    <img src={datas.thumbnail} alt="thumbnail" width="100%" height="100%" />
                                ) : (
                                    <img src={`${process.env.PUBLIC_URL}/Image/noimg.png`} alt="no_img" width="100%" height="100%" />
                                )}
                            </div>
                            <div className="hotel-info">
                                <p className="hotel-title">{datas.title}</p>
                                <p className="hotel-area">{areastring(datas.areaCode)}</p>
                                <p className="hotel-date">{formatTime(datas.modifiedDate)}</p>
                            </div>
                        </div>
                    ))
                ) : (
                    <p>No data available.</p>
                )}
            </div>
        </div>
    );
}

export default HotelsList;
