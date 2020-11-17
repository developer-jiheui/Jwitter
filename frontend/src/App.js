import React, {useEffect} from 'react';
import Login from './components/user/Login'
import Sidebar from './components/left/Sidebar'
import Main from './components/middle/Main'
import Profile from './components/middle/Profile'
import Embed from './components/right/Embed'
import Bookmark from './components/middle/Bookmark'
import Notifications from './components/middle/Notifications'
import { BrowserRouter as Router, Switch, Route } from "react-router-dom";
import './App.css';
import { useStateValue } from "./utils/StateProvider";
import SidebarOption from "./components/left/SidebarOption";


function App() {
  const [{}, dispatch] = useStateValue();

  useEffect(() => {
    // check if session exist
    // const session = localStorage.getItem('myData');
    // or create login service in sercive.js
    const auth = {};
    if (auth) {
      // the user just logged in / the user was logged in
      dispatch({
        type: "SET_USER",
        user: auth,
      });
    } else {
      // the user is logged out
      dispatch({
        type: "SET_USER",
        user: null,
      });
    }
  },[]);

  return (
  //   <div className="app">
  //   {!token && <Login />}
  //   {token && 
  //   <>
  //   <Sidebar/>
  //   <Main/>
  //   <Embed/>
  //   </>
  //   }
  // </div>
  <Router>
      <div className="app">
        <Switch>
          <Route path="/home">
          <Sidebar ativeOne="Home"/>
          <Main/>
          <Embed/>
          </Route>
          <Route path="/login">
            <Login />
          </Route>
          <Route path="/profile">
          <Sidebar ativeOne="Profile"/>
            <Profile/>
            <Embed/>
          </Route>
          <Route path="/notifications">
            <Sidebar activeOne="Notifications"/>
            <Notifications/>
            <Embed/>
          </Route>
          <Route path="/bookmarks">
            <Sidebar activeOne="Bookmark"/>
            <Bookmark/>
            <Embed/>
          </Route>
          <Route path="/">
            <Login />
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
