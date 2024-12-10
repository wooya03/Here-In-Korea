import React, { useEffect, useState } from 'react';
import '../css/HotelsList.css';
import { format } from "date-fns";
import axios from "axios";

function formatTime(dateString) {
    const date = new Date(dateString);
    return format(date, "yyyy-MM-dd h:mm:ss a");
}

function HotelsList() {
    const baseUrl = "http://localhost:8080";
    const [data, setData] = useState([]);

    useEffect(() => {
        putSpringData();
    }, []);

    
    async function putSpringData() {
        await axios
            .get(baseUrl + "/hotels/list")
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

    function areastring(res){
        switch(res){
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
        }
    }
    return (
        <div className="hotels-container">
            {data.length > 0 ? (
                data.map((datas, index) => (
                    <div key={index} className="hotel-card">
                        <div className="hotel-image">
                            {datas.thumbnail ? (
                                <img src={datas.thumbnail} alt="thumbnail" width="100%" height="100%" />
                            ) : (
                                <img src={`${process.env.PUBLIC_URL}/Image/noimg.png`} alt="no_img" width="100%" height="100%" />
                            )}
                        </div>
                        <div className="hotel-info">
                            <p className="hotel-title">{datas.title}</p>
                            <p className="hotel-area">
                                {areastring(datas.areaCode)}</p>
                            <p className="hotel-date">{formatTime(datas.modifiedDate)}</p>
                        </div>
                    </div>
                ))
            ) :(
                <p>No data available.</p>
            )}
        </div>
    );
}

export default HotelsList;