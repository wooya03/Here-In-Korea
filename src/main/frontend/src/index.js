import React from 'react';
import ReactDOM from 'react-dom/client';
import {BrowserRouter} from "react-router-dom";
import Footer from "./global/footer/Footer.js";
import App from './App';
import Header from "./global/header/Header";

const root = ReactDOM.createRoot(document.getElementById('root'));
root.render(
  <BrowserRouter>
    <App />
    <Footer />
  </BrowserRouter>
);