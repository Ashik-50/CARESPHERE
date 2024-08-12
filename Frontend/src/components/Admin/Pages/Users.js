import React, { useEffect, useState } from 'react';
import AdminNavbar from './AdminNavbar';

const Users = () => {
  const [users, setUsers] = useState([]);
  const [search, setSearch] = useState('');

  const fetchUsers = async () => {
    try {
      const response = await fetch('http://localhost:8080/login');
      if (!response.ok) {
        throw new Error('Failed to fetch users');
      }
      const data = await response.json();
      setUsers(data);
    } catch (error) {
      console.error('Error:', error);
    }
  };

  const deleteUser = async (id) => {
    try {
      const response = await fetch(`http://localhost:8080/login/${id}`, {
        method: 'DELETE',
      });
      if (!response.ok) {
        throw new Error('Failed to delete user');
      }
      setUsers(users.filter((user) => user.id !== id));
    } catch (error) {
      console.error('Error:', error);
    }
  };

  useEffect(() => {
    fetchUsers();
  }, []);

  const handleSearch = (e) => {
    setSearch(e.target.value);
  };

  const roleTagStyle = {
    backgroundColor: '#000', // Black background for all roles
    color: '#fff' // White text color for visibility
  };

  const capitalizeFirstLetter = (text) => {
    return text.charAt(0).toUpperCase() + text.slice(1).toLowerCase();
  };

  return (
    <div>
      <AdminNavbar />
      <div className="user-page" style={{ display: 'flex', flexDirection: 'column', marginTop: '90px', height: '100vh', backgroundColor: '#f4f7f6' }}>
        <main style={{ flex: 1, padding: '20px', display: 'flex', flexDirection: 'column', alignItems: 'center' }}>
          <div className="search-area" style={{ width: '100%', maxWidth: 600, marginBottom: 20, display: 'flex', justifyContent: 'center' }}>
            <input
              type="text"
              placeholder="Search users..."
              onChange={handleSearch}
              value={search}
              style={{
                width: '100%',
                padding: '15px',
                fontSize: '16px',
                borderRadius: '8px',
                border: '1px solid #ddd',
                boxShadow: '0 2px 6px rgba(0,0,0,0.1)',
                backgroundColor: '#fff',
                outline: 'none',
                transition: 'box-shadow 0.3s ease, border-color 0.3s ease'
              }}
              onFocus={(e) => e.target.style.boxShadow = '0 4px 12px rgba(0,0,0,0.2)'}
              onBlur={(e) => e.target.style.boxShadow = '0 2px 6px rgba(0,0,0,0.1)'}
              onKeyDown={(e) => e.key === 'Enter' && e.target.blur()}
            />
          </div>
          <div className="user-list" style={{ display: 'grid', gridTemplateColumns: 'repeat(auto-fill, minmax(280px, 1fr))', gap: '20px', width: '100%', maxWidth: 1200 }}>
            {users.filter(user => 
              user.username.toLowerCase().includes(search.toLowerCase()) ||
              user.firstName.toLowerCase().includes(search.toLowerCase()) ||
              user.lastName.toLowerCase().includes(search.toLowerCase()) ||
              user.email.toLowerCase().includes(search.toLowerCase())
            ).map((user, index) => (
              <div key={index} className="user-card" style={{
                backgroundColor: '#fff',
                border: '1px solid #ddd',
                padding: '20px',
                borderRadius: '12px',
                boxShadow: '0 6px 12px rgba(0,0,0,0.1)',
                transition: 'transform 0.3s ease, box-shadow 0.3s ease',
                cursor: 'pointer',
                overflow: 'hidden',
                display: 'flex',
                flexDirection: 'column',
                alignItems: 'center',
                minHeight: '200px' // Ensures there's enough space for the content
              }}
              onMouseEnter={e => e.currentTarget.style.transform = 'scale(1.03)'}
              onMouseLeave={e => e.currentTarget.style.transform = 'scale(1)'}
              >
                <div className="user-info" style={{ textAlign: 'center' }}>
                  <h3 className="username" style={{
                    margin: '10px 0',
                    fontSize: '24px',
                    fontWeight: '600',
                    color: '#333' // Ensures the text is visible against the background
                  }}>
                    {capitalizeFirstLetter(user.username)}
                  </h3>
                  <span style={{
                    display: 'block',
                    marginBottom: '10px',
                    padding: '4px 8px',
                    borderRadius: '12px',
                    fontSize: '12px', // Reduced size for the role
                    fontWeight: '500',
                    backgroundColor: roleTagStyle.backgroundColor,
                    color: roleTagStyle.color
                  }}>
                    {user.role.charAt(0).toUpperCase() + user.role.slice(1)}
                  </span>
                  <p className="email" style={{
                    margin: '10px 0',
                    color: '#007bff',
                    fontSize: '16px',
                    textDecoration: 'none',
                    transition: 'color 0.3s'
                  }}>
                    <a href={`mailto:${user.email}`} style={{ color: '#007bff', textDecoration: 'none' }}>
                      {user.email}
                    </a>
                  </p>
                </div>
                {user.role !== 'admin' && (
                  <button
                    onClick={() => deleteUser(user.id)}
                    style={{
                      marginTop: '10px',
                      padding: '10px 20px',
                      backgroundColor: '#dc3545',
                      color: '#fff',
                      border: 'none',
                      borderRadius: '8px',
                      cursor: 'pointer',
                      transition: 'background-color 0.3s ease, transform 0.3s ease',
                      fontSize: '14px'
                    }}
                    onMouseEnter={e => e.currentTarget.style.backgroundColor = '#c82333'}
                    onMouseLeave={e => e.currentTarget.style.backgroundColor = '#dc3545'}
                    onMouseDown={e => e.currentTarget.style.transform = 'scale(0.98)'}
                    onMouseUp={e => e.currentTarget.style.transform = 'scale(1)'}
                  >
                    Delete User
                  </button>
                )}
              </div>
            ))}
          </div>
        </main>
      </div>
    </div>
  );
};

export default Users;
