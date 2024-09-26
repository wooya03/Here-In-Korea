import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from "react-router-dom";
import Header from "./global/Header.js";
import Footer from "./global/Footer.js";
import App from './App';

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <Header />
    <App />
    <Footer />
  </BrowserRouter>
);