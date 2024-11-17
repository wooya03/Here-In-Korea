import React, { Component } from 'react';
import '../css/QuestionAdd.css';

class QuestionAdd extends Component {
  constructor(props) {
    super(props);
    this.state = {
      questionType: '행사 문의',
      questionTitle: '',
      questionContent: ''
    };
  }

  handleTypeChange = (event) => {
    this.setState({ questionType: event.target.value });
  };

  handleTitleChange = (event) => {
    this.setState({ questionTitle: event.target.value });
  };

  handleContentChange = (event) => {
    this.setState({ questionContent: event.target.value });
  };

  handleSubmit = () => {
    // 질문 등록 로직 추가 (예: 상태 업데이트, 서버 전송 등)
    alert('질문이 등록되었습니다!');
  };

  render() {
    return (
      <div className="question-add-container">
        <div className="question-add-header">
          <input
            type="text"
            value={this.state.questionTitle}
            onChange={this.handleTitleChange}
            placeholder="제목을 입력하세요"
            className="question-title-input"
          />
          <button className="submit-button" onClick={this.handleSubmit}>
            등록
          </button>
        </div>
        <div className="question-type">
          <select value={this.state.questionType} onChange={this.handleTypeChange}>
            <option value="행사 문의">행사 문의</option>
            <option value="숙소 문의">숙소 문의</option>
            <option value="기타">기타</option>
          </select>
        </div>
        <textarea
          className="question-content"
          value={this.state.questionContent}
          onChange={this.handleContentChange}
          placeholder="질문 내용을 입력하세요"
        />
      </div>
    );
  }
}

export default QuestionAdd;