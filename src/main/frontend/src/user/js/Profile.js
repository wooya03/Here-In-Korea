import React, { useState } from 'react';
import '../css/Profile.css'; // 외부 CSS 파일을 사용할 경우

const Profile = () => {
    // 각 필드에 대한 상태를 설정
    const [isEditingEmail, setIsEditingEmail] = useState(false);
    const [isEditingName, setIsEditingName] = useState(false);
    const [isEditingDob, setIsEditingDob] = useState(false);
    const [isEditingBirthday, setIsEditingBirthday] = useState(false);

    const [email, setEmail] = useState("user@example.com");
    const [name, setName] = useState("사용자 이름");
    const [dob, setDob] = useState("1990-01-01");
    const [birthday, setBirthday] = useState("01-01");

    // 수정 버튼 클릭 시 상태 변경
    const toggleEdit = (field) => {
        switch (field) {
            case "email":
                setIsEditingEmail(!isEditingEmail);
                break;
            case "name":
                setIsEditingName(!isEditingName);
                break;
            case "dob":
                setIsEditingDob(!isEditingDob);
                break;
            case "birthday":
                setIsEditingBirthday(!isEditingBirthday);
                break;
            default:
                break;
        }
    };

    return (
        <div className="profile-container">
            {/* 왼쪽 구역 */}
            <div className="left-section">
                <div className="profile-image">
                    <img src={`${process.env.PUBLIC_URL}/Image/noimg.png`} alt="Profile Image" />
                </div>
                <div className="user-id">
                    <p>사용자아이디</p>
                </div>
                <div className="menu">
                    <ul>
                        <li><a href="#">내 프로필 보기</a></li>
                        <li><a href="#">비밀번호 변경</a></li>
                        <li><a href="#">내 게시글 관리</a></li>
                    </ul>
                </div>
            </div>

            {/* 오른쪽 구역 */}
            <div className="right-section">
                <div className="profile-details">
                    {/* 이메일 */}
                    <div className="detail-item">
                        <label htmlFor="email">이메일</label>
                        {isEditingEmail ? (
                            <input
                                type="text"
                                id="email"
                                value={email}
                                onChange={(e) => setEmail(e.target.value)}
                            />
                        ) : (
                            <p>{email}</p>
                        )}
                        <button className="edit-btn" onClick={() => toggleEdit("email")}>
                            {isEditingEmail ? "저장" : "수정"}
                        </button>
                    </div>

                    {/* 이름 */}
                    <div className="detail-item">
                        <label htmlFor="name">이름</label>
                        {isEditingName ? (
                            <input
                                type="text"
                                id="name"
                                value={name}
                                onChange={(e) => setName(e.target.value)}
                            />
                        ) : (
                            <p>{name}</p>
                        )}
                        <button className="edit-btn" onClick={() => toggleEdit("name")}>
                            {isEditingName ? "저장" : "수정"}
                        </button>
                    </div>

                    {/* 생년월일 */}
                    <div className="detail-item">
                        <label htmlFor="dob">생년월일</label>
                        {isEditingDob ? (
                            <input
                                type="text"
                                id="dob"
                                value={dob}
                                onChange={(e) => setDob(e.target.value)}
                            />
                        ) : (
                            <p>{dob}</p>
                        )}
                        <button className="edit-btn" onClick={() => toggleEdit("dob")}>
                            {isEditingDob ? "저장" : "수정"}
                        </button>
                    </div>

                    {/* 생일 */}
                    <div className="detail-item">
                        <label htmlFor="birthday">생일</label>
                        {isEditingBirthday ? (
                            <input
                                type="text"
                                id="birthday"
                                value={birthday}
                                onChange={(e) => setBirthday(e.target.value)}
                            />
                        ) : (
                            <p>{birthday}</p>
                        )}
                        <button className="edit-btn" onClick={() => toggleEdit("birthday")}>
                            {isEditingBirthday ? "저장" : "수정"}
                        </button>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default Profile;
