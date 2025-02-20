import { useState, useEffect } from "react";
import { useNavigate, Link } from "react-router-dom";
import styled from "styled-components";
import "bootstrap/dist/css/bootstrap.min.css";
import cartimage from '../Home/cartimage.png';

// Styled Components
const MainScreen = styled.div`
  min-height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: #dfdbe5;
  background-image: url("https://wallpaperaccess.com/full/3063067.png");
  color: #963e7b;
  align-items: center;
  justify-content: center;
`;

const Card = styled.div`
  width: 60rem;
  background: white;
  border-radius: 1.5rem;
  box-shadow: 4px 3px 20px rgba(53, 53, 53, 0.55);
  display: flex;
  flex-direction: row;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  transform-style: preserve-3d;

  &:hover {
    transform: scale(1.05);
    box-shadow: 6px 6px 25px rgba(53, 53, 53, 0.7);
  }
`;


const LeftSide = styled.div`
  background:rgb(247, 244, 244);
  width: 25rem;
  display: flex;
  align-items: center;
  justify-content: center;
  border-top-left-radius: 1.5rem;
  border-bottom-left-radius: 1.5rem;
`;

const ProductImage = styled.img`
  width: 360px;
  height: 360px;
  border-radius: 50%;
  object-fit: cover;
`;

const RightSide = styled.div`
  background-color: #ffffff;
  width: 35rem;
  border-bottom-right-radius: 1.5rem;
  border-top-right-radius: 1.5rem;
  padding: 1rem 2rem 3rem 3rem;
`;

const Input = styled.input`
  width: 100%;
  padding: 0.5rem;
  border: none;
  border-bottom: 1.5px solid #ccc;
  margin-bottom: 1rem;
  border-radius: 0.3rem;
  font-size: 1.1rem;
  font-weight: 500;
  outline: none;
  transition: all 0.3s ease-in-out;
  &:focus {
    border-bottom: 1.5px solid #753370;
    box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.15);
  }
`;

const Button = styled.button`
  background: linear-gradient(135deg, #753370 0%, #298096 100%);
  padding: 15px;
  border: none;
  border-radius: 50px;
  color: white;
  font-weight: 400;
  font-size: 1.2rem;
  margin-top: 10px;
  width: 100%;
  letter-spacing: 0.11rem;
  outline: none;
  transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
  &:hover {
    transform: scale(1.05) translateY(-3px);
    box-shadow: 3px 3px 6px rgba(56, 55, 55, 0.52);
  }
`;

const Login = () => {
  const [email, setEmail] = useState("");
  const [password, setPassword] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [isLoggedIn, setIsLoggedIn] = useState(false);
  const navigate = useNavigate();

  useEffect(() => {
    const token = sessionStorage.getItem("token");
    setIsLoggedIn(!!token);
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    setLoading(true);
    setError("");

    try {
      const response = await fetch("http://localhost:8282/signin", {
        method: "POST",
        headers: { "Content-Type": "application/json" },
        body: JSON.stringify({ email, password }),
      });

      const data = await response.json();
      setLoading(false);

      if (response.ok) {
        sessionStorage.setItem("token", data.token);
        sessionStorage.setItem("user", JSON.stringify(data.user));
        window.dispatchEvent(new Event("storage"));
        setIsLoggedIn(true);
        navigate("/");
      } else {
        setError(data.message || "Invalid login credentials");
      }
    } catch (error) {
      setLoading(false);
      setError("Failed to connect to the server");
    }
  };

  return (
    <MainScreen>
      <Card>
        <LeftSide>
          <ProductImage src={cartimage} alt="Cart Image" />
        </LeftSide>
        <RightSide>
          <h2 className="text-center text-primary">{isLoggedIn ? "Welcome" : "Login"}</h2>
          {error && <div className="alert alert-danger">{error}</div>}

          {!isLoggedIn ? (
            <form onSubmit={handleSubmit}>
              <div className="mb-3">
                <label htmlFor="email" className="form-label">Email</label>
                <Input
                  type="email"
                  id="email"
                  placeholder="Enter your email"
                  value={email}
                  onChange={(e) => setEmail(e.target.value)}
                  required
                />
              </div>
              <div className="mb-3">
                <label htmlFor="password" className="form-label">Password</label>
                <Input
                  type="password"
                  id="password"
                  placeholder="Enter your password"
                  value={password}
                  onChange={(e) => setPassword(e.target.value)}
                  required
                />
              </div>
              <Button type="submit" disabled={loading}>
                {loading ? "Logging in..." : "Login"}
              </Button>
              <p className="mt-3 text-center">
                Don't have an account? <Link to="/signup">Create New User</Link>
              </p>
            </form>
          ) : (
            <div className="text-center">
              <p>Welcome back! You're logged in.</p>
              <Button onClick={() => {
                sessionStorage.removeItem("token");
                sessionStorage.removeItem("user");
                setIsLoggedIn(false);
                navigate("/login");
              }}>
                Logout
              </Button>
            </div>
          )}
        </RightSide>
      </Card>
    </MainScreen>
  );
};

export default Login;
