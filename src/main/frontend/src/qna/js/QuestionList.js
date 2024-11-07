// import React from 'react';

// class QuestionList extends React.Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             questions: [
//                 {
//                 id: 4,
//                 type: '숙소 문의',
//                 title: '나무호텔 문의 드립니다.',
//                 status: '문의 확인중입니다.',
//                 answered: false,
//                 date: '2024.06.13 18:31'
//                 },
//                 {
//                 id: 3,
//                 type: '행사 문의',
//                 title: '한강 서래섬 유채꽃 축제 주차공간',
//                 status: '답변 완료',
//                 answered: true,
//                 date: '2024.06.04 22:54'
//                 }
//             ]
//         }
//     }
//     render(){
//         return (
//             <div className="container">
//               <div className="search-bar">
//                 <select>
//                   <option>문의 구분</option>
//                   <option value="숙소 문의">숙소 문의</option>
//                   <option value="행사 문의">행사 문의</option>
//                 </select>
//                 <input type="text" placeholder="검색" />
//                 <button>검색</button>
//                 <button>질문하기</button>
//               </div>
              
//               <div className="question-list">
//                 {questions.map((question) => (
//                   <div key={question.id} className="question-card">
//                     <div className="question-header">
//                       <span className="question-id">No {question.id}</span>
//                       <span className="question-date">{question.date}</span>
//                     </div>
//                     <h3 className="question-title">{question.title}</h3>
//                     <div className="question-status">
//                       {question.answered ? (
//                         <span className="status answered">✔ {question.status}</span>
//                       ) : (
//                         <span className="status pending">✖ {question.status}</span>
//                       )}
//                     </div>
//                   </div>
//                 ))}
//               </div>
//             </div>
//           );
//     }
// }

// export default QuestionList;