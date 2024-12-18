import React, { useState } from 'react';
import '../css/Profile.css';
import { useNavigate } from 'react-router-dom';

const Profile = () => {
    const [selectedMenu, setSelectedMenu] = useState('profile');
    const [isEditingEmail, setIsEditingEmail] = useState(false);
    const [isEditingName, setIsEditingName] = useState(false);
    const [isEditingDob, setIsEditingDob] = useState(false);
    const [isEditingGender, setIsEditingGender] = useState(false);

    const [email, setEmail] = useState("user@example.com");
    const [name, setName] = useState("사용자 이름");
    const [dob, setDob] = useState("1990-01-01");
    const [gender, setGender] = useState("남성");
    const [profileImage, setProfileImage] = useState(`${process.env.PUBLIC_URL}/Image/profile_base_img.jpg`); // 초기 프로필 이미지

    const navigate = useNavigate();

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
            case "gender":
                setIsEditingGender(!isEditingGender);
                break;
            default:
                break;
        }
    };

    // 이미지 파일 변경 핸들러
    const handleImageChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => {
                setProfileImage(reader.result); // 파일을 읽은 후 상태에 저장
            };
            reader.readAsDataURL(file); // 이미지 파일을 base64 형식으로 읽음
        }
    };

    // 메뉴 클릭 핸들러
    const handleMenuClick = (menu) => {
        setSelectedMenu(menu);
    };

    // 내 게시글 목록 (예시 데이터)
    const renderPosts = () => {
        switch (selectedMenu) {
            case 'myPosts':
                return (
                    <section className="posts_list">
                        <header className="posts_header">
                            <div>번호</div>
                            <div>게시판 종류</div>
                            <div>제목</div>
                        </header>
                        <hr />
                        <article className="post_item">
                            <div>1</div>
                            <div>코스게시판</div>
                            <div>여행 코스 1</div>
                        </article>
                        <article className="post_item">
                            <div>2</div>
                            <div>리뷰</div>
                            <div>좋은 리뷰</div>
                        </article>
                        <article className="post_item">
                            <div>3</div>
                            <div>문의</div>
                            <div>여행 정보 문의</div>
                        </article>
                    </section>
                );
            default:
                return null;
        }
    };

    return (
        <section className="profile_container">
            {/* 왼쪽 구역 */}
            <section className="left_section">
                <section className="profile_image">
                    <img src={profileImage} alt="Profile Image" className="profile_img"/>
                    {/* 프로필 이미지 우측 하단에 수정 아이콘 */}
                    <label htmlFor="profile-image-upload" className="edit_icon">
                        <img src={`${process.env.PUBLIC_URL}/Image/edit_img.png`} alt="Edit" className="edit_icon_img"/>
                    </label>
                    <input
                        type="file"
                        id="profile-image-upload"
                        style={{ display: 'none' }} // 기본 파일 입력은 숨김
                        accept="image/*"
                        onChange={handleImageChange}
                    />
                </section>
                <section className="user_id">
                    <p>사용자아이디</p>
                </section>
                <nav className="menu">
                    <ul>
                        <li><a href="#" onClick={() => handleMenuClick('profile')}>내 프로필 보기</a></li>
                        <li><a href="#" onClick={() => navigate("/user/find/pw")}>비밀번호 변경</a></li>
                        <li><a href="#" onClick={() => handleMenuClick('myPosts')}>내 게시글 관리</a></li>
                    </ul>
                </nav>
            </section>

            {/* 오른쪽 구역 */}
            <section className="right_section">
                {/* 상단 메뉴 */}
                {selectedMenu === 'profile' ? (
                    <section className="profile_details">
                        {/* 이메일 */}
                        <section className="detail_item">
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
                            <button className="edit_btn" onClick={() => toggleEdit("email")}>
                                {isEditingEmail ? "저장" : "수정"}
                            </button>
                        </section>

                        {/* 이름 */}
                        <section className="detail_item">
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
                            <button className="edit_btn" onClick={() => toggleEdit("name")}>
                                {isEditingName ? "저장" : "수정"}
                            </button>
                        </section>

                        {/* 생년월일 */}
                        <section className="detail_item">
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
                            <button className="edit_btn" onClick={() => toggleEdit("dob")}>
                                {isEditingDob ? "저장" : "수정"}
                            </button>
                        </section>

                        {/* 성별 */}
                        <section className="detail_item">
                            <label htmlFor="gender">성별</label>
                            {isEditingGender ? (
                                <section className="gender">
                                    <label>
                                        <input
                                            type="radio"
                                            value="남성"
                                            checked={gender === "남성"}
                                            onChange={() => setGender("남성")}
                                        />
                                        남성
                                    </label>
                                    <label>
                                        <input
                                            type="radio"
                                            value="여성"
                                            checked={gender === "여성"}
                                            onChange={() => setGender("여성")}
                                        />
                                        여성
                                    </label>
                                </section>
                            ) : (
                                <p>{gender}</p>
                            )}
                            <button className="edit_btn" onClick={() => toggleEdit("gender")}>
                                {isEditingGender ? "저장" : "수정"}
                            </button>
                        </section>
                    </section>
                ) : (
                    // 내 게시글 관리 UI
                    renderPosts()
                )}
            </section>
        </section>
    );
};

export default Profile;
