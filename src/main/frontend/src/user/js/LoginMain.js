import React from "react";
import "../css/LoginMain.css";

class LoginMain extends React.Component {
    render() {
        return (
            <div className="content">
                <div className="login_wrap">
                    <ul className="menu_wrap">
                    </ul>
                    <form className="fm_login" id="fm_login">
                        <ul className="panel_wrap">
                            <li className="panel_item">
                                <div className="panel_inner">
                                    <div className="login_from">
                                        <div className="login_box">
                                            <div className="input_item_id">
                                                <input type="text" className="input_id" id="id" value=""/>
                                            </div>
                                            <div className="input_item_id">
                                                <input type="text" className="input_pw" id="pw" value=""/>
                                            </div>
                                        </div>
                                    </div>
                                    <div></div>
                                </div>
                            </li>
                        </ul>
                    </form>

                </div>

            </div>


        )
    }
}

export default LoginMain;
