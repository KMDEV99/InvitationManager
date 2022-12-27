import '../App.css';
import { useState } from 'react';

function RegisterNewUser() {

  const [errorMessages, setErrorMessages] = useState([]);

  const registerUser = (e) => {
    e.preventDefault()

    const requestOptions = {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        firstName: e.target[0].value,
        lastName: e.target[1].value,
        email: e.target[2].value
      })
    };

    fetch('http://localhost:8080/api/user', requestOptions)
      .then((response) => {
        if (response.ok) {
          document.getElementById('registerForm').reset()
          document.getElementById('errors').innerHTML = ''
          alert("User created successfully")
          document.location.reload()
        }
        return response.json()
      })
      .then((data) => {
        setErrorMessages(data.errors)
      });

  }

  return (
    <div className="registerNewUserPanel">
      <form onSubmit={registerUser} method="post" id="registerForm">
        <h2>Register New User</h2>
        <label>
          First Name:&emsp;
          <input name="firstName" />
        </label>
        <br /><br />
        <label>
          Last Name:&emsp;
          <input name="lastName" />
        </label>
        <br /><br />
        <label>
          Email:&emsp;&emsp;&emsp;
          <input name="email" />
        </label>
        <br /><br />
        <input type="submit" value="Submit" />
      </form>

      <div className='errors' id="errors">
        {errorMessages.map(errorMessage => {
          return (
            <p key={errorMessage.toString()}>{errorMessage}</p>
          )
        })}
      </div>
    </div>
  );
}

export default RegisterNewUser;
