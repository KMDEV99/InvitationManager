import '../App.css';
import RegisterNewUser from './RegisterNewUser';
import UserComponent from './UserComponent';
import InviteUserComponent from './InviteUserComponent';

function App() {
  return (
    <div className="app">
      <div className="column">
        <UserComponent />
      </div>
      <div className="column">
        <h1>Admin Panel</h1>
        <RegisterNewUser />
        <InviteUserComponent />
      </div>
    </div>
  );
}

export default App;
