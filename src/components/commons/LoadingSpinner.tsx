import React from "react";

interface LoadingSpinnerProps {
  message?: string;
}

const LoadingSpinner: React.FC<LoadingSpinnerProps> = ({ message }) => {
  return (
    <div className="flex flex-col items-center justify-center h-64">
      <div className="animate-spin rounded-full h-16 w-16 border-t-4 border-blue-500"></div>
      {message && <p className="mt-4 text-blue-500">{message}</p>}
    </div>
  );
};

export default LoadingSpinner;
