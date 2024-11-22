import React, { useEffect } from 'react';
import './Translate_api.css';

const GoogleTranslate = () => {
  useEffect(() => {
    // Google 번역 스크립트 로드
    const addGoogleTranslateScript = () => {
      const script = document.createElement('script');
      script.type = 'text/javascript';
      script.src = '//translate.google.com/translate_a/element.js?cb=googleTranslateElementInit';
      script.onload = () => {
        console.log("Google Translate script loaded successfully");
      };
      script.onerror = (error) => {
        console.error("Error loading Google Translate script", error);
      };
      document.body.appendChild(script);
    };

    // Google 번역 초기화 함수
    window.googleTranslateElementInit = () => {
      new window.google.translate.TranslateElement(
        {
          pageLanguage: 'ko', // 원래 페이지 언어
          includedLanguages: 'en,ja,ko', 
          layout: window.google.translate.TranslateElement.InlineLayout.SIMPLE // 번역 버튼 레이아웃
        },
        'google_translate_element'
      );
    };

    addGoogleTranslateScript();
  }, []);

  return (
    <div>
      <div id="google_translate_element"></div>
    </div>
  );
};

export default GoogleTranslate;
