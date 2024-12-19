import React, { createContext, useState, useEffect, useContext } from "react";

// AuthContext 생성
const AuthContext = createContext();

// AuthProvider 컴포넌트
export const AuthProvider = ({ children }) => {
    const [isLoggedIn, setIsLoggedIn] = useState(false); // 로그인 상태

    // 로그인 상태 확인 함수 (페이지 마운트 시 호출)
    const checkLoginStatus = async () => {
        const accessToken = localStorage.getItem("token");
        const refreshToken = localStorage.getItem("refreshToken");

        if (!accessToken) {
            setIsLoggedIn(false);
            return;
        }

        try {
            // AccessToken 유효성 검사 (예: API 호출)
            const response = await fetch("http://localhost:8080/user/loginck", {
                method: "POST",
                headers: {
                    Authorization: `Bearer ${accessToken}`,
                },
            });

            if (response.ok) {
                setIsLoggedIn(true);
            } else if (response.status === 401 && refreshToken) {
                // AccessToken이 만료되었을 경우, RefreshToken으로 재발급
                const refreshResponse = await fetch("http://localhost:8080/api/auth/refreshToken", {
                    method: "POST",
                    headers: {
                        "Content-Type": "application/json",
                    },
                    body: JSON.stringify({ refreshToken }),
                });

                if (refreshResponse.ok) {
                    const data = await refreshResponse.json();
                    localStorage.setItem("token", data.accessToken); // 새 토큰 저장
                    setIsLoggedIn(true);
                } else {
                    setIsLoggedIn(false);
                }
            } else {
                setIsLoggedIn(false);
            }
        } catch (error) {
            console.error("로그인 상태 확인 중 오류 발생:", error);
            setIsLoggedIn(false);
        }
    };

    // 컴포넌트 마운트 시 로그인 상태를 체크
    useEffect(() => {
        checkLoginStatus();
    }, []);

    return (
        <AuthContext.Provider value={{ isLoggedIn, checkLoginStatus }}>
            {children}
        </AuthContext.Provider>
    );
};

// useAuth 훅을 사용하여 AuthContext의 값을 쉽게 사용할 수 있도록 함
export const useAuth = () => {
    return useContext(AuthContext);
};

export default AuthContext;