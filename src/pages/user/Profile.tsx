interface props {
  name?: string;
}

function Profile(props: props) {
  return <>{props.name}</>;
}

export default Profile;
