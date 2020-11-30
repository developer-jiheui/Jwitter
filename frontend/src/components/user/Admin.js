import React, { useState, useEffect } from "react";
import { Button, ButtonGroup, Container,Table} from '@material-ui/core';
import axios from 'axios';
//TODO add reports
function Admin() {
  const [users, setUsers] = useState([]);
  const [reports, setReports] = useState([]);
  useEffect(() => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
    axios({
      method: 'get',
      url: '/api/admin/reports',
      headers: {
        Authorization: bearer
      }
    }).then(resp => {
      setReports(resp.data)
    }).catch(r => {
      console.log(r)
    });
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

  const disableReport = (report) => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
    axios({
      method: 'post',
      url: '/api/admin/tweets/' + report.postId + '/disable',
      headers: {
        Authorization: bearer
      }
    }).then(_ => {
      report.disabled = true
      window.location.reload();
    }).catch(r => {
      console.log(r)
    });
  }
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

  const reportList  = reports.map(report => {
      return <tr key={report.postId}>
                <td>{report.postId}</td>
                <td>{report.reports}</td>
                <td>{report.content}</td>
                <td>
                  <ButtonGroup>
                    <Button size="sm" disabled={report.disabled === true} color="danger" onClick={() => disableReport(report)}>Disable</Button>
                    <Button size="sm" disabled={report.disabled === false} color="primary" onClick={() => enableReport(report)}>Enable</Button>
                  </ButtonGroup>
                </td>
              </tr>
    });
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


  const enableReport = (report) => {
    let bearer = 'Bearer ' + JSON.parse(JSON.stringify(localStorage.getItem('jwt')));
    axios({
      method: 'post',
      url: '/api/admin/tweets/' + report.postId + '/enable',
      headers: {
        Authorization: bearer
      }
    }).then(_ => {
      report.disabled = false
      window.location.reload();
    }).catch(r => {
      console.log(r)
    });
  }

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
              <th width="20%">Disable</th>
            </tr>
          </thead>
          <tbody>
            {userList}
          </tbody>
        </Table>
		  <br/>
		  <h3>Reports</h3>

        <Table className="mt-4">
          <thead>
            <tr>
              <th width="20%">PostId</th>
              <th width="20%">Reports</th>
              <th width="20%">Content</th>
              <th width="20%">Action</th>
            </tr>
          </thead>
          <tbody>
            {reportList}
          </tbody>
        </Table>
      </Container>
    </div>
  )
}

export default Admin;
