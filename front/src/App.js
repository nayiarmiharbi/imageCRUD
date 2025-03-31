import React, { useState, useEffect } from 'react';
import axios from 'axios';
import './App.css';

function App() {
  const [images, setImages] = useState([]);
  const [file, setFile] = useState(null);
  const [updateFile, setUpdateFile] = useState(null);

  useEffect(() => {
    fetchImages();
  }, []);

  const fetchImages = async () => {
    const response = await axios.get('http://localhost:8081/api/images');
    setImages(response.data);
  };

  const handleFileChange = (e) => {
    setFile(e.target.files[0]);
  };

  const handleUpdateFileChange = (e) => {
    setUpdateFile(e.target.files[0]);
  };

  const uploadImage = async () => {
    const formData = new FormData();
    formData.append('file', file);
    await axios.post('http://localhost:8081/api/images/upload', formData);
    fetchImages();
    setFile(null);
  };

  const updateImage = async (id) => {
    const formData = new FormData();
    formData.append('file', updateFile);
    await axios.put(`http://localhost:8081/api/images/${id}`, formData);
    fetchImages();
    setUpdateFile(null);
  };

  const deleteImage = async (id) => {
    await axios.delete(`http://localhost:8081/api/images/${id}`);
    fetchImages();
  };

  return (
    <div className="App">
      <h1>Image CRUD Application</h1>

      {/* Upload Form */}
      <div>
        <input type="file" onChange={handleFileChange} />
        <button onClick={uploadImage} disabled={!file}>Upload</button>
      </div>

      {/* Image List */}
      <div>
        {images.map((image) => (
          <div key={image.id} style={{ margin: '20px' }}>
            <img
              src={`http://localhost:8081/api/images/${image.id}`}
              alt={image.name}
              style={{ maxWidth: '200px' }}
            />
            <p>{image.name}</p>
            <input type="file" onChange={handleUpdateFileChange} />
            <button onClick={() => updateImage(image.id)} disabled={!updateFile}>Update</button>
            <button onClick={() => deleteImage(image.id)}>Delete</button>
          </div>
        ))}
      </div>
    </div>
  );
}

export default App;