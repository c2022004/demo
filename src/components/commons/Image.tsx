import { useState, forwardRef } from "react";
import defaultImage from "../../assets/img/defaultImage.jpg";

interface ImageProps extends React.ImgHTMLAttributes<HTMLImageElement> {
  src?: string;
  alt?: string;
  fallBack?: string;
  imgSmall?: boolean;
  classes?: string;
}

const Image = forwardRef<HTMLImageElement, ImageProps>(
  ({ src, alt, imgSmall, classes, ...props }, ref) => {
    const [fallBack, setFallBack] = useState("");

    const handleError = () => {
      setFallBack(defaultImage);
    };

    classes = " overflow-hidden object-cover";
    if (imgSmall) {
      classes += " w-22";
    }
    return (
      <img
        className={classes}
        ref={ref}
        src={src || fallBack}
        alt={alt}
        {...props}
        onError={handleError}
      />
    );
  }
);

export default Image;
