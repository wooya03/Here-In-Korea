import React, { useState, useEffect } from 'react';
import '../css/Profile.css';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';

const Profile = () => {
    const [selectedMenu, setSelectedMenu] = useState('profile');
    const [isEditingEmail, setIsEditingEmail] = useState(false);
    const [isEditingName, setIsEditingName] = useState(false);
    const [isEditingDob, setIsEditingDob] = useState(false);
    const [isEditingGender, setIsEditingGender] = useState(false);

    const [memId, setMemId] = useState('사용자ID');
    const [memName, setMemName] = useState('사용자 이름');
    const [gender, setGender] = useState('M'); // 초기값: 'M' 또는 'F'
    const [birth, setBirth] = useState('1970-01-01');
    const [email, setEmail] = useState('이메일@aaa.com');
    const [profileImage, setProfileImage] = useState(`${process.env.PUBLIC_URL}/Image/profile_base_img.jpg`);

    const navigate = useNavigate();

    useEffect(() => {
        const token = localStorage.getItem('token');
        if(!token){
            alert("로그인이 필요합니다!")
            navigate('/');
        }
        else if (token) {
            axios.get('http://localhost:8080/user/profile', {
                headers: {
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }
            })
                .then(response => {
                    const user = response.data;
                    setMemId(user.memId);
                    setMemName(user.memName);
                    setEmail(user.email);
                    setBirth(user.birth);
                    setGender(user.gender === 'M' ? 'M' : 'F');
                    setProfileImage(user.profileImage || `${process.env.PUBLIC_URL}/Image/profile_base_img.jpg`);
                })
                .catch(error => {
                    console.error("프로필 정보를 가져오는 중 오류 발생:", error);
                });
        }
    }, []);

    const toggleEdit = (field) => {
        switch (field) {
            case "email":
                if (isEditingEmail) saveProfile();
                setIsEditingEmail(!isEditingEmail);
                break;
            case "name":
                if (isEditingName) saveProfile();
                setIsEditingName(!isEditingName);
                break;
            case "dob":
                if (isEditingDob) saveProfile();
                setIsEditingDob(!isEditingDob);
                break;
            case "gender":
                if (isEditingGender) saveProfile();
                setIsEditingGender(!isEditingGender);
                break;
            default:
                break;
        }
    };

    const handleImageChange = (e) => {
        const file = e.target.files[0];
        if (file) {
            const reader = new FileReader();
            reader.onloadend = () => setProfileImage(reader.result);
            reader.readAsDataURL(file);
        }
    };

    const validateDate = (date) => {
        const dateRegex = /^\d{4}-\d{2}-\d{2}$/;
        return dateRegex.test(date);
    };

    const saveProfile = () => {
        const token = localStorage.getItem('token');
        if (!validateDate(birth)) {
            alert("생년월일은 yyyy-MM-dd 형식으로 입력해주세요.");
            return;
        }

        const updatedProfile = {
            memName,
            email,
            birth,
            gender
        };

        axios.put('http://localhost:8080/user/profile', updatedProfile, {
            headers: {
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            }
        })
            .then(response => {
                alert("프로필이 성공적으로 업데이트되었습니다.");
                setIsEditingEmail(false);
                setIsEditingName(false);
                setIsEditingDob(false);
                setIsEditingGender(false);
            })
            .catch(error => {
                console.error("프로필 업데이트 실패:", error);
                alert("프로필 업데이트에 실패했습니다.");
            });
    };

    const handleMenuClick = (menu) => setSelectedMenu(menu);

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
            <section className="left_section">
                <section className="profile_image">
                    <img src={profileImage} alt="Profile Image" className="profile_img"/>
                    <label htmlFor="profile-image-upload" className="edit_icon">
                        <img src={`${process.env.PUBLIC_URL}/Image/edit_img.png`} alt="Edit" className="edit_icon_img"/>
                    </label>
                    <input
                        type="file"
                        id="profile-image-upload"
                        style={{ display: 'none' }}
                        accept="image/*"
                        onChange={handleImageChange}
                    />
                </section>
                <section className="user_id">
                    <p>{memId}</p>
                </section>
                <nav className="menu">
                    <ul>
                        <li><a onClick={() => handleMenuClick('profile')}>내 프로필 보기</a></li>
                        <li><a onClick={() => navigate("/user/find/pw")}>비밀번호 변경</a></li>
                        <li><a onClick={() => handleMenuClick('myPosts')}>내 게시글 관리</a></li>
                    </ul>
                </nav>
            </section>

            <section className="right_section">
                {selectedMenu === 'profile' ? (
                    <section className="profile_details">
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

                        <section className="detail_item">
                            <label htmlFor="name">이름</label>
                            {isEditingName ? (
                                <input
                                    type="text"
                                    id="name"
                                    value={memName}
                                    onChange={(e) => setMemName(e.target.value)}
                                />
                            ) : (
                                <p>{memName}</p>
                            )}
                            <button className="edit_btn" onClick={() => toggleEdit("name")}>
                                {isEditingName ? "저장" : "수정"}
                            </button>
                        </section>

                        <section className="detail_item">
                            <label htmlFor="dob">생년월일</label>
                            {isEditingDob ? (
                                <input
                                    type="text"
                                    id="dob"
                                    value={birth}
                                    onChange={(e) => setBirth(e.target.value)}
                                />
                            ) : (
                                <p>{birth}</p>
                            )}
                            <button className="edit_btn" onClick={() => toggleEdit("dob")}>
                                {isEditingDob ? "저장" : "수정"}
                            </button>
                        </section>

                        <section className="detail_item">
                            <label htmlFor="gender">성별</label>
                            {isEditingGender ? (
                                <section className="gender">
                                    <label>
                                        <input
                                            type="radio"
                                            value="M"
                                            checked={gender === "M"}
                                            onChange={() => setGender("M")}
                                        />
                                        남성
                                    </label>
                                    <label>
                                        <input
                                            type="radio"
                                            value="F"
                                            checked={gender === "F"}
                                            onChange={() => setGender("F")}
                                        />
                                        여성
                                    </label>
                                </section>
                            ) : (
                                <p>{gender === "M" ? "남성" : "여성"}</p>
                            )}
                            <button className="edit_btn" onClick={() => toggleEdit("gender")}>
                                {isEditingGender ? "저장" : "수정"}
                            </button>
                        </section>
                    </section>
                ) : (
                    renderPosts()
                )}
            </section>
        </section>
    );
};

export default Profile;
