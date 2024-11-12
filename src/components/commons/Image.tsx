import { useState, forwardRef } from "react";

interface ImageProps extends React.ImgHTMLAttributes<HTMLImageElement> {
  src?: string;
  alt: string;
  fallBack?: string;
}

const defaultFallbackImage = "https://example.com/default-image.png";

const Image = forwardRef<HTMLImageElement, ImageProps>(
  (
    { src, alt, fallBack: customFallback = defaultFallbackImage, ...props },
    ref
  ) => {
    const [fallBack, setFallBack] = useState("");

    const handleError = () => {
      setFallBack(customFallback);
    };

    return (
      <img
        className="overflow-hidden"
        ref={ref}
        src={fallBack || src}
        alt={alt}
        {...props}
        onError={handleError}
      />
    );
  }
);

export default Image;
