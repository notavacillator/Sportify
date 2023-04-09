import { useNavigate } from "react-router";

const Unauthorized = () => {
  const navigate = useNavigate;
  const goBack = navigate(-1);
  return (
    <div>
      <h1>Unauthorized</h1>
      <br />
      <p>You do not have access to the requested page.</p>
      <button onClick={goBack}>Go Back</button>
    </div>
  );
};

export default Unauthorized;
