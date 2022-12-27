import '../App.css';
import { useEffect, useState } from 'react';

function UserComponent() {

  const [users, setUsers] = useState([])

  const fetchUsers = () => {
    fetch('http://localhost:8080/api/user')
      .then((response) => response.json())
      .then((data) => {
        setUsers(data)
      })
  }

  const acceptInvite = (invitationId) => {
    const requestOptions = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
    };
    fetch('http://localhost:8080/api/invitation/accept/' + invitationId, requestOptions)
    document.location.reload()
  }

  const declineInvite = (invitationId) => {
    const requestOptions = {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
    };
    fetch('http://localhost:8080/api/invitation/decline/' + invitationId, requestOptions)
    document.location.reload()
  }

  useEffect(() => {
    fetchUsers()
  }, [])


  return (
    <div className="users">
      <h1>Users and their invitations</h1>
      {users.map(user => {
        return (
          <div key={user.id} className="user">
            <h2>Id: {user.id}</h2>
            <h2>Name: {user.firstName}</h2>
            <h2>Surname: {user.lastName}</h2>
            <h2>Email: {user.email}</h2>
            <table key={user.invitations}>
              <tbody>
                <tr>
                  <th>Description</th>
                  <th>From</th>
                  <th>Status</th>
                  <th>Action</th>
                </tr>
                {user.invitations.map(invitation =>
                  <tr key={invitation.id}>
                    <td>{invitation.description}</td>
                    <td>{invitation.inviterEmail}</td>
                    <td>{invitation.status}</td>
                    <td>
                      <input type="button" value="Accept" onClick={() => acceptInvite(invitation.id)} disabled={invitation.status === 'ACCEPTED'} />
                      <input type="button" value="Decline" onClick={() => declineInvite(invitation.id)} disabled={invitation.status === 'DECLINED'} />
                    </td>
                  </tr>)
                }
              </tbody>
            </table>
          </div>
        );
      })}
    </div>
  );
}

export default UserComponent;
