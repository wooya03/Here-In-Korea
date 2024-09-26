import React from "react";
import '../css/AdminLogin.css';

class AdminLogin extends React.Component {
    render() {
        return (
        <div>
          <form>
            <h2 id='login_title'> 로그인</h2>
            <div className='admin_login'>
            <div>
                {/* 아이디 */}
                <div>
                <input type='text' class="input-field" maxLength='20' name='admin_id'
                placeholder="ID" />
                </div>

                {/* 비밀번호 */}
                <div>
                <input type='password' class="input-field" maxLength='15' name='admin_password' placeholder="PASSWORD"/>
                </div>
              </div>
            </div>
            <div>
            <button type="submit" id="sbtn">로그인&nbsp;</button>
            </div>
        </form>
      </div>

        )
    }
}

export default AdminLogin;