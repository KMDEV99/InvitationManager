import '../App.css';
import { useState } from 'react';

function InviteUserComponent() {

  const [errorMessages, setErrorMessages] = useState([]);

  const inviteUser = (e) => {
    e.preventDefault()

    if (e.target[0].value !== "") {
      const requestOptions = {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify({
          inviteeEmail: e.target[0].value,
          description: e.target[2].value,
          inviterEmail: e.target[1].value
        })
      };

      fetch('http://localhost:8080/api/invitation/', requestOptions)
        .then((response) => {
          if (response.ok) {
            document.getElementById('inviteUserForm').reset()
            document.getElementById('InviteErrors').innerHTML = ''
            alert("Invite sent successfully")
            document.location.reload()
          }
          return response.json()
        })
        .then((data) => {
          setErrorMessages(data.errors)
        })
    }

  }

  return (
    <div className="inviteUserPanel">
      <form onSubmit={inviteUser} method="post" id="inviteUserForm">
        <h2>Send Invite to User</h2>
        <label>
          Invitee Email:<br />
          <input name="inviteeEmail" />
        </label>
        <br /><br />
        <label>
          Inviter Email:<br />
          <input name="inviterEmail" />
        </label>
        <br /><br />
        <label>
          Description:<br />
          <input name="description" />
        </label>
        <br /><br />
        <input type="submit" value="Submit" />
      </form>

      <div className='errors' id="InviteErrors">
        {errorMessages.map(errorMessage => {
          return (
            <p key={errorMessage.toString()}>{errorMessage}</p>
          )
        })}
      </div>
    </div>
  );
}

export default InviteUserComponent;
