import React from 'react';

class ErrorBoundary extends React.Component {
    constructor(props) {
        super(props);
        this.state = { hasError: false };
    }

    static getDerivedStateFromError(error) {
        if (error.message && error.message.includes('Script error.')) {
            // "Script error." 메시지를 무시
            return { hasError: false };
        }
        return { hasError: true };
    }

    componentDidCatch(error, errorInfo) {
        if (error.message && error.message.includes('Script error.')) {
            // "Script error." 메시지 로깅도 무시
            return;
        }
        console.error('Error caught by ErrorBoundary:', error, errorInfo);
    }
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                          
    render() {
        if (this.state.hasError) {
            return <h1>Something went wrong.</h1>;
        }
        return this.props.children;
    }
}
window.onerror = function (message, source, lineno, colno, error) {
    if (message === "Script error.") {
        return true; // "Script error." 숨기기
    }
    console.error('Unhandled error:', message, source, lineno, colno, error);
};
export default ErrorBoundary;
