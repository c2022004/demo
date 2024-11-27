import React from "react";
interface Props {
  title?: string;
}

function Profile({ title = "Default Title" }: Props) {
  return <>{title}</>;
}

export default Profile;