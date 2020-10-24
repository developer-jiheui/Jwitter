import {config} from '../config';
export const service ={
    login,
}
function login(signinEmail,signinPassword){
    // fetch('http://localhost:3000/signin', {
    //         method: 'post',
    //         headers: {'Content-Type': 'application/json'},
    //         body: JSON.stringify({
    //         email: signinEmail,
    //         password: signinPassword
    //         })
    //     }).then(response => response.json())
    const requestOptions = {
        method: 'post',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({ email:signinEmail, password:signinPassword })
    };
    return fetch(`htts://..`, requestOptions)
        .then(data => {
            // store user details and jwt token in local storage to keep user logged in between page refreshes
            // localStorage.setItem('user', JSON.stringify(user));
            //return data;
            return { user: 'Anita', name:'haha'}
        });
    }