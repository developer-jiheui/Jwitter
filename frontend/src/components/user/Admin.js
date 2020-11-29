import React, { useState, useEffect } from "react";
import { Button, ButtonGroup, Container,Table} from '@material-ui/core';
import axios from 'axios';
//TODO add reports
function Admin() {
  const [users, setUsers] = useState([]);
  useEffect(() => {
    console.log("HERE")
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
    axios({
      method: 'get',
      url: '/api/admin/users',
      headers: {
        Authorization: bearer
      }
    }).then(resp => {
      setUsers(resp.data)
      
    }).catch(r => {
      console.log(r)
    });
  }, []);

  const disable = (user) => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
    axios({
      method: 'post',
      url: '/api/admin/users/' + user.id + '/disable',
      headers: {
        Authorization: bearer
      }
    }).then(_ => {
      user.disabled = true
      window.location.reload();
    }).catch(r => {
      console.log(r)
    });
  }

  const userList  = users.map(user => {
      return <tr key={user.id}>
                <td>{user.email}</td>
                <td>{user.username}</td>
                <td>
                  <ButtonGroup>
                    <Button size="sm" disabled={user.disabled === true} color="danger" onClick={() => disable(user)}>Disable</Button>
                    <Button size="sm" disabled={user.disabled === false} color="primary" onClick={() => enable(user)}>Enable</Button>
                  </ButtonGroup>
                </td>
              </tr>
    });


  const enable = (user) => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
    axios({
      method: 'post',
      url: '/api/admin/users/' + user.id + '/enable',
      headers: {
        Authorization: bearer
      }
    }).then(_ => {
      user.disabled = false
      window.location.reload();
    }).catch(r => {
      console.log(r)
    });
  }

  return (
    <div>
      <Container fluid>
        <h3>Users</h3>
        <Table className="mt-4">
          <thead>
            <tr>
              <th width="20%">Email</th>
              <th width="20%">Username</th>
              <th width="10%">Disable</th>
            </tr>
          </thead>
          <tbody>
            {userList}
          </tbody>
        </Table>
      </Container>
    </div>
  )
}

export default Admin;
